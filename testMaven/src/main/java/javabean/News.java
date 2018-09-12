package javabean;



public class News {
	private String newsId;//新闻id
	private String author;//作者
	private String newsType;//新闻类型
	private String caption;//标题
	private String content;//内容
	private String newsTime;//新闻时间
	private String publishTime;//发布时间
	private String newsEnable;
	public News() {
	}
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNewsTime() {
		return newsTime;
	}
	public void setNewsTime(String newsTime) {
		this.newsTime = newsTime;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	/**
	 * @return the newsEnable
	 */
	public String getNewsEnable() {
		return newsEnable;
	}
	/**
	 * @param newsEnable the newsEnable to set
	 */
	public void setNewsEnable(String newsEnable) {
		this.newsEnable = newsEnable;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "作者="+author+"新闻类型="+newsType+"标题="+caption;
	}
	
}
