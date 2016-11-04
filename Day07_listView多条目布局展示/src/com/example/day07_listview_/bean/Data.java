package com.example.day07_listview_.bean;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Data {
	public String title;
	public String share_url;
	public String comment_count;
	public String source;
/////////////////图片////////////////
	public List<ImageList> image_list;
	public MiddleImage middle_image;
//	public LargeImageList large_image_list;
	public List<LargeImageList> large_image_list;
	
}
