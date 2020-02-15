package com.baidu.ueditor.upload;
 
import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
 
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
 
import jdk.internal.util.xml.impl.Input;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
 
public class BinaryUploader {
 
	/**
	 * 修改BinaryUploader类，解决其无法获得带字节流的request的问题
	 */
	public static final State save(HttpServletRequest request,
			Map<String, Object> conf) {
 
//		FileItemStream fileStream = null;
//		boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;
 
		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}
 
//		ServletFileUpload upload = new ServletFileUpload(
//				new DiskFileItemFactory());
//
//        if ( isAjaxUpload ) {
//            upload.setHeaderEncoding( "UTF-8" );
//        }
 
		try {
//			FileItemIterator iterator = upload.getItemIterator(request);
//
//			while (iterator.hasNext()) {
//				fileStream = iterator.next();
//
//				if (!fileStream.isFormField())
//					break;
//				fileStream = null;
//			}
//
//			if (fileStream == null) {
//				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
//			}
 
			/*========== 添加的内容 ==========*/
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multipartRequest.getFile(conf.get("fieldName").toString());
			if (multipartFile == null) {
				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
			}
			// 添加结束
 
			String savePath = (String) conf.get("savePath");
//			String originFileName = fileStream.getName();
			String originFileName = multipartFile.getOriginalFilename(); //　修改的
			String suffix = FileType.getSuffixByFilename(originFileName);
 
			originFileName = originFileName.substring(0,
					originFileName.length() - suffix.length());
			savePath = savePath + suffix;
 
			long maxSize = ((Long) conf.get("maxSize")).longValue();
 
			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}
 
			savePath = PathFormat.parse(savePath, originFileName);
 
			String physicalPath = (String) conf.get("rootPath") + savePath;
 
//			InputStream is = fileStream.openStream();
			InputStream is = multipartFile.getInputStream();
			State storageState = StorageManager.saveFileByInputStream(is,
					physicalPath, maxSize);
			is.close();
 
			if (storageState.isSuccess()) {
				storageState.putInfo("url", PathFormat.format(savePath));
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}
 
			return storageState;
//		} catch (FileUploadException e) {
//			return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}
 
	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);
 
		return list.contains(type);
	}
}