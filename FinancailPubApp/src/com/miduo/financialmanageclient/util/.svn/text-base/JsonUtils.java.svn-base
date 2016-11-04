package com.miduo.financialmanageclient.util;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.miduo.financialmanageclient.ws.AppException;

public class JsonUtils {
	private static GsonBuilder gsonb = null;
	static {
		gsonb = new GsonBuilder();
	}

	public static <T> T toBean(String jsonString, Type type) throws AppException {
		try {
			// Gson gson = gsonb.create();
			final String dateformat = "yyyy-MM-dd HH:mm:ss";
			Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(Date.class, new JsonSerializer<Date>() {
				public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
					SimpleDateFormat format = new SimpleDateFormat(dateformat,Locale.getDefault());
					return new JsonPrimitive(format.format(src));
				}
			}).setDateFormat(dateformat).create();
			LOG.out("--JsonUtils.toBean--jsonString:" + jsonString);
			if (jsonString != null && !jsonString.equals("{}")  && !jsonString.equals("[]")) {
				return gson.fromJson(jsonString, type);
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw AppException.json(e);
		}
	}

	public static String toString(Object bean) throws AppException {
		try {
			Gson gson = gsonb.create();
			return gson.toJson(bean);
		} catch (Exception e) {
			e.printStackTrace();
			throw AppException.json(e);
		}
	}
}
