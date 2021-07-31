package com.wms.service.helper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ObjectHelper<T> {
	
	/**
	 * A method that paginating the List<Item> to Page<Item>
	 * 
	 * @param itemlist the input itemlist
	 * @param pageNum  page number
	 * @param pageSize page size
	 * @return Pagination of items.
	 */
	public static <T> Page<T> listToPageCovert(List<T> itemlist, int pageNum, int pageSize) {
		
		Pageable pagination = new PageRequest(pageNum, pageSize);

		int start = pagination.getOffset();
		int end = (start + pagination.getPageSize()) > itemlist.size() ? itemlist.size()
				: (start + pagination.getPageSize());

		if (start > end) {
			throw new RuntimeException("Bad pagination request");
		}

		Page<T> page = new PageImpl<T>(itemlist.subList(start, end), pagination, itemlist.size());

		return page;
	}

}
