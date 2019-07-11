package com.bh.common.utils;

import net.sf.json.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.Cookie;
import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于模拟HTTP请求中GET/POST方式
 * @author landa
 *
 */
public class HttpRequestUtils {
	/**
	 * 发送GET请求
	 *
	 * @param url
	 *            目的地址
	 * @param parameters
	 *            请求参数，Map类型。
	 * @return 远程响应结果
	 */
	public static String sendGet(String url, Map<String, String> parameters) {
		String result="";
		BufferedReader in = null;// 读取响应输入流
		StringBuffer sb = new StringBuffer();// 存储参数
		String params = "";// 编码之后的参数
		try {
			// 编码请求参数
			if(parameters.size()==1){
				for(String name:parameters.keySet()){
					sb.append(name).append("=").append(
							java.net.URLEncoder.encode(parameters.get(name),
									"UTF-8"));
				}
				params=sb.toString();
			} else if(parameters.size() > 1){
				for (String name : parameters.keySet()) {
					sb.append(name).append("=").append(
							java.net.URLEncoder.encode(parameters.get(name),
									"UTF-8")).append("&");
				}
				String temp_params = sb.toString();
				params = temp_params.substring(0, temp_params.length() - 1);
			}
            String full_url = "";
			if(params == ""){
                full_url = url;
            } else {
                full_url = url + "?" + params;
            }
			System.out.println(full_url);
			// 创建URL对象
			java.net.URL connURL = new java.net.URL(full_url);
			// 打开URL连接
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
					.openConnection();
			// 设置通用属性
			httpConn.setRequestProperty("Accept", "*/*");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
			// 建立实际的连接
			httpConn.connect();
			// 响应头部获取
			Map<String, List<String>> headers = httpConn.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : headers.keySet()) {
				System.out.println(key + "\t：\t" + headers.get(key));
			}
			// 定义BufferedReader输入流来读取URL的响应,并设置编码方式
			in = new BufferedReader(new InputStreamReader(httpConn
					.getInputStream(), "UTF-8"));
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result ;
	}

	/**
	 * 发送POST请求
	 *
	 * @param url
	 *            目的地址
	 * @param parameters
	 *            请求参数，Map类型。
	 * @return 远程响应结果
	 */
	public static String sendPost(String url, Map<String, String> parameters, Map<String, String> headers, Cookie[] cookies) {
		String result = "";// 返回的结果
		BufferedReader in = null;// 读取响应输入流
		PrintWriter out = null;
		StringBuffer sb = new StringBuffer();// 处理请求参数
		String params = "";// 编码之后的参数
		try {
			// 编码请求参数
			if (parameters.size() == 1) {
				for (String name : parameters.keySet()) {
					sb.append(name).append("=").append(
							java.net.URLEncoder.encode(parameters.get(name),
									"UTF-8"));
				}
				params = sb.toString();
			} else if (parameters.size() > 1) {
				for (String name : parameters.keySet()) {
					sb.append(name).append("=").append(
							java.net.URLEncoder.encode(parameters.get(name),
									"UTF-8")).append("&");
				}
				String temp_params = sb.toString();
				params = temp_params.substring(0, temp_params.length() - 1);
			}
			// 创建URL对象
			java.net.URL connURL = new java.net.URL(url);
			// 打开URL连接
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
					.openConnection();

			// 设置通用属性
            httpConn.setRequestProperty("Charset", "UTF-8");
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            if (headers.size() >= 1) {
                for (String name : headers.keySet()) {
                    httpConn.setRequestProperty(name, headers.get(name));
                }
            }
            if(cookies != null){
            	String cookie = "";
				for (int i = 0; i < cookies.length; i++) {
					// System.out.println(cookies[i].getName() + ":" + cookies[i].getValue());
					cookie += cookies[i].getName() + "=" + cookies[i].getValue() + ";";
				}
				if (cookie != ""){
					cookie = cookie.substring(0, cookie.length() - 1);
					httpConn.setRequestProperty("Cookie", cookie);
				}
			}
			//设置请求方式
			httpConn.setRequestMethod("POST");
			// 发送POST请求必须设置如下两行
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			// 获取HttpURLConnection对象对应的输出流
			out = new PrintWriter(httpConn.getOutputStream());
			// 发送请求参数
			out.write(params);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应，设置编码方式
			in = new BufferedReader(new InputStreamReader(httpConn
					.getInputStream(), "UTF-8"));
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 上传文件的请求方法
	 *
	 * @param urlStr 请求地址
	 * @return
	 */
	public static String httpUpload(String urlStr, List<InputStream> files, Map<String, String> params) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			httpClient = HttpClients.createDefault();

			// 把一个普通参数和文件上传给下面这个地址 是一个servlet
			HttpPost httpPost = new HttpPost(urlStr);

			// 编码请求参数
			MultipartEntityBuilder multipart = MultipartEntityBuilder.create();

			if(!files.isEmpty() && files.size() > 0){
				for (int i = 0; i<files.size(); i++) {
					multipart.addBinaryBody("attachments", files.get(i));
				}
			}


			for (String name : params.keySet()) {
				StringBody param = new StringBody(params.get(name), ContentType.create(
						"text/plain", Consts.UTF_8));
				multipart.addPart(name, param);
			}

			HttpEntity reqEntity = multipart.build();

			httpPost.setEntity(reqEntity);

			// 发起请求 并返回请求的响应
			response = httpClient.execute(httpPost);

			// 获取响应对象
			HttpEntity resEntity = response.getEntity();

			if (resEntity != null) {
				return EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
			}

			// 销毁
			EntityUtils.consume(resEntity);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(response != null){
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				if(httpClient != null){
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	/**
	 * 主函数，测试请求
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> parameters = new HashMap<String, String>();
        JSONObject json = new JSONObject();
        json.put("userName", "Johnny5");
        json.put("companyName", "Johnny4");
        json.put("contactNo", "090912345679");
        json.put("contactPerson", "John doe4");
        json.put("companyAddress", "4th benning");
        parameters.put("Details", json.toString());
        parameters.put("key", "WaRQqEcpnE2A1hTwobHvQA7kklsjC0Fk");
        parameters.put("Address", "");
        parameters.put("Pubkey", "");

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type","application/x-www-form-urlencoded");
//        headers.put("x-mobile-app","true");
//		String result =sendGet("http://18.138.35.246/rs/get_syspars", parameters);
        String result = sendPost("http://18.138.35.246/rs/register_wallet", parameters, headers, null);
        System.out.println("结果---------------->" + result);
        JSONObject obj = JSONObject.fromObject(result);
        System.out.print(obj.get("error"));
	}
}