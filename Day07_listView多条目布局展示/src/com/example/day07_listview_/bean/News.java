package com.example.day07_listview_.bean;


import java.util.List;

public class News {
	public List<MyData> data;
	public String has_more;
	public String has_more_to_refresh;
	public String login_status;
	public String message;
	public String total_number;

	public class MyData {
		public List<ImageList> image_list;
		public String source;
		public String title;
		public String display_url;
		public String comment_count;
		public List<LargeList> large_image_list;
		public Middleimage middle_image;

	}

	public class LargeList {
		public String url;
	}

	public class ActionList {
		public String url;
		public String desc;
		public String extra;
	}

	public class FilterWords {
		public String id;
		public String is_selected;
		public String name;
	}

	public class ImageList {
		public String height;
		public String uri;
		public String url;
		public List<UrlList> url_list;
		public String width;
	}

	public class UrlList {
		public String url;
	}

	public class Middleimage {
		public String height;
		public String uri;
		public String url;
		public List<UrlList> url_list;
		public String width;
	}
}
