package katachi.jobfinders.domain.model;

import java.util.Iterator;
import java.util.List;

public class Paginator<T> implements Iterable<T> {

	private List<T> list;
	private int total;
	private int lastPage;
	private int perPage;
	private int currentPage;
	private String path;
	private List<String> queries;

	public Paginator(List<T> list, int total, int perPage, int currentPage, String path, List<String> queries) {
		super();
		this.list = list;
		this.total = total;
		this.lastPage = total / perPage + 1;
		this.perPage = perPage;
		this.currentPage = currentPage;
		this.path = path;
		this.queries = queries;
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	public int getTotal() {
		return total;
	}

	public int getLastPage() {
		return lastPage;
	}

	public int getPerPage() {
		return perPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public String getPath() {
		return path;
	}

	public int getCurrentItemFrom() {
		return (currentPage - 1) * perPage + 1;
	}

	public int getCurrentItemTo() {
		return currentPage * perPage > total ? total : currentPage * perPage;
	}

	public String getQuery() {
		String queryString = "";
		for (int i = 0; i < queries.size(); i++) {
			if (i == 0) {
				queryString = queries.get(i);
			} else {
				queryString += "&" + queries.get(i);
			}
		}
		return queryString;
	}

	public String getQueryWithPage(int page) {
		String query = getQuery();
		return query + (query.length() > 0 ? "&page=" : "page=") + page;
	}

	public boolean isOmitted(int page) {
		if (isEllipsis(page)) {
			return true;
		}
		if (lastPage > 13) {
			if (currentPage > 7) {
				if (3 < page && page < lastPage - 9 && page < currentPage - 3) {
					return true;
				}
			}
			if (currentPage + 6 < lastPage) {
				if (currentPage + 3 < page && page + 6 > lastPage && page < lastPage - 2) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isEllipsis(int page) {
		if (lastPage > 13) {
			if (currentPage > 7) {
				if (3 == page) {
					return true;
				}
			}
			if (currentPage + 6 < lastPage) {
				if (page == lastPage - 2) {
					return true;
				}
			}
		}
		return false;
	}
}
