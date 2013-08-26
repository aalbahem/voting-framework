package voting.provider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TopDocs;

import voting.Voter;
import voting.VoterProvider;

public class LuceneProvider implements VoterProvider{

	private TopDocs rankedDocument;
	private int next;
	private String aggregatorFld;
	private IndexSearcher searcher;
	
	public LuceneProvider(TopDocs rankedDocs, IndexSearcher searcher, String aggregatorFld)
	{
		this.rankedDocument = rankedDocs;
		next = (rankedDocs.totalHits > 0?0:-1);
		this.aggregatorFld = aggregatorFld;
		this.searcher  = searcher;
	}
	
	@Override
	public Voter nextVoter() {
		if  (!hasNext()){
			return null;
		}
		
		Document doc;
		try {
			doc = searcher.doc(rankedDocument.scoreDocs[next].doc);
			return new Voter(next+1,rankedDocument.scoreDocs[next++].score, doc.get(aggregatorFld) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public boolean hasNext() {
		if (next == -1 ) return false;
		if (next >= rankedDocument.scoreDocs.length) return false;
		return true;
		
	}

	@Override
	public int size() {
		return rankedDocument.scoreDocs.length;
	}

	
	public Map<String, List<String>> groupVoters() throws Exception {
	   if (! (size() > 0))
		   throw new Exception("Nothing to group");
	   
	   
	   Map<String, List<String>> docsMap = new HashMap<String, List<String>>();
	   
	   for (int i = 0; i < rankedDocument.scoreDocs.length; i++){
		   Document doc = searcher.doc(rankedDocument.scoreDocs[i].doc);
		   
		   List<String> docList = new LinkedList<String>();
		   String key = doc.get(aggregatorFld);
		   if (docsMap.containsKey(key)){
			  docList = docsMap.get(key);
		   }
		   
		   docList.add(String.valueOf(rankedDocument.scoreDocs[i].doc));
		   docsMap.put(key, docList);
	   }
	   
	   return docsMap;
	}
}
