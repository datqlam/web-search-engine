package search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import search.service.Result;
import search.service.SearchEngineService;

@RestController
public class SearchEngineController {

	@Autowired
	public SearchEngineService ses;

	@ModelAttribute
	public void setResponseHeader(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
	}

	@RequestMapping("/search")
	public ResponsedSearchResult search(@RequestParam Map<String, String> requestParams) {
		String query = requestParams.get("q");
		String page = requestParams.get("page");
		String size = requestParams.get("size");
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxX Parameters are " + query);
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxX Parameters are " + page);
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxX Parameters are " + size);
		Result rs = ses.findByQuery(query);
		Map<String, Integer> occurences = rs.getSortedOccurencesOfAllKeys();
		System.out.println("zzzzzzzzzzzzzzzzzzzzzz " + occurences);
		ResponsedSearchResult result = new ResponsedSearchResult();
		result.setTotal(occurences.size());
		List<Hit> hits = new ArrayList<>();
		Integer resultRange = Integer.valueOf(size);
		Integer resultPage = Integer.valueOf(page);
		if (occurences.size() <= resultRange) {
			occurences.forEach((k, v) -> hits.add(new Hit(new Metadata(k, "" + v))));
		} else {
			int startIndex = (resultPage - 1) * 10;
			int endIndex = ((resultPage * 10) > resultRange) ? occurences.size() : resultPage * 10;

			int i = 0;
			for (Map.Entry<String, Integer> entry : occurences.entrySet()) {
				if (startIndex <= i && i < endIndex) {
					hits.add(new Hit(new Metadata(entry.getKey(), "" + entry.getValue())));
				}
				i++;
			}
		}

		result.setHits(hits);
		return result;
	}
}
