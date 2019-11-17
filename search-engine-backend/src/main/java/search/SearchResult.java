package search;

import java.util.List;

public class SearchResult {

    private long id;
    private String content;
    private long total1;
    private List<Hit> hits;

    public SearchResult(long id, String content) {
        this.id = id;
        this.content = content;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getTotal1() {
		return total1;
	}

	public void setTotal1(long total1) {
		this.total1 = total1;
	}

	public List<Hit> getHits() {
		return hits;
	}

	public void setHits(List<Hit> hits) {
		this.hits = hits;
	}
}
