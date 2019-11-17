package search;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchEngineController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @ModelAttribute
	public void setResponseHeader(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
	}    

    @RequestMapping("/search")
    public SearchResult search(@RequestParam(value="name", defaultValue="World") String name) {
    	SearchResult result = new SearchResult(counter.incrementAndGet(), String.format(template, name));
    	result.setTotal1(12345L);
    	List<Hit> hits = new ArrayList<> ();
    	hits.add(new Hit(new Metadata("test", "testDecsription")));
    	result.setHits(hits);
        return result;
    }
}
