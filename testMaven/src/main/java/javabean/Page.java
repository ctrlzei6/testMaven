package javabean;

public class Page {
	private	int start; // 开始数据的索引
	private	int count; // 每一页的数量 
	private	int total; // 总数据量 
	
	public Page(){}
	
	public Page(int start, int count) 
	{ 
		this.start = start;
		this.count = count; 
	} 
	
	public Page(int start, int count,int total) 
	{ 
		this.start = start;
		this.count = count; 
		this.total = total;
	}
	
	/*获得当前页 */ 
	public int getPageNow()
	{ 
		return start/count + 1; 
	} 
	
	/*判断是否有上一页 */ 
	public boolean isHasPreviouse()
	{ 
		if(start-count<0) return false; 
		return true; 
	} 
	/*获得上一页 */ 
	public int getPrePage()
	{ 
		if(isHasPreviouse()) 
			return start-count; 
		return start; 
	} 
	
	/*判断是否有下一页 */ 
	public boolean isHasNext()
	{ 
		if(start>=getLast()) return false; 
		return true; 
	} 
	public int getNextPage()
	{ 
		if(isHasNext()) 
			return start+count; 
		return start; 
	} 
	/* 计算得到总页数 */ 
	public int getTotalPage()
	{ 
		int totalPage; 
		if (0 == total % count) // 假设总数是50，是能够被5整除的，那么就有10页 
			totalPage = total /count; 
		else // 假设总数是51，不能够被5整除的，那么就有11页
			totalPage = total / count + 1; 
		
		//totalPage=(total % count==0) ? total /count : total / count + 1; 
		
		if(0==totalPage) 
			totalPage = 1; 
		return totalPage; 
	} 
	
	/*计算得到尾页 */ 
	public int getLast()
	{ 
		int last; 
		// 假设总数是50，是能够被5整除的，那么最后一页的开始就是45 
		if (0 == total % count) 
			last = total - count; 
		// 假设总数是51，不能够被5整除的，那么最后一页的开始就是50 
		else 
			last = total - total % count; 
		last = last<0?0:last; 
		return last; 
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	} 

}
