package cn.bill.sbjparediscache.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Description:文件上传工具类 <br/>
 * Date:2018年8月6日 下午3:30:43 <br/>
 *
 * @author fengminbiao@126.com
 * @version
 */
public class FileUpload
{
	
	private FileUpload(){}
	/**
	 * 文件上传<br/>
	 * 注意：如果上传的文件保存在Tomcat工程目录下的upload文件夹中，如D:\\Program Files\\apache-tomcat-8.0.52\\webapps\\MySpringmvc4\\upload\\
	 * 每次重新部署，会重新创建upload文件夹，则原来的upload文件夹会被清空掉，所以，upload文件夹不能放在项目里面，而是放在跟项目文件夹同级目录下：
	 * D:\\Program Files\\apache-tomcat-8.0.52\\webapps\\upload\\
	 * @param multipartRequest
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> uploadFiles(MultipartHttpServletRequest multipartRequest) throws Exception
	{
		System.out.println("FileUpload....uploadFiles()");
		
		Map<String, String> filePathMap = new HashMap<String, String>();

		Map<String, MultipartFile> multiPartFileMap = multipartRequest.getFileMap();
		if (multiPartFileMap != null)
		{
			for (Entry<String, MultipartFile> multiPartFileEntry : multiPartFileMap.entrySet())
			{
				if (multiPartFileEntry != null)
				{
					String key = multiPartFileEntry.getKey();
					MultipartFile multipartFile = multiPartFileEntry.getValue();
					if(multipartFile != null)
					{
						long fileSize = multipartFile.getSize();
						if (fileSize > 0)
						{
							System.out.println(key + " == " + "(" + multipartFile.getName() + ")" + multipartFile.getOriginalFilename() + "  fileSize:"+fileSize);
							//获取上传文件的原文件名
							String originalFilename = multipartFile.getOriginalFilename();
							//对应applicationSocpe对象，被所有用户共享，只要服务器没有关闭，都存活
							/*ServletContext servletContext = multipartRequest.getServletContext();
							String webInfPath = servletContext.getRealPath("WEB-INF");
							String uploadBasePath = webInfPath.split("webapps")[0] + "webapps\\upload";*/
							File uploadFolder = new File("F:\\upload");
							if(!uploadFolder.exists())
							{
								uploadFolder.mkdirs();
							}
							String fileName = UUID.randomUUID().toString().trim()+"_"+originalFilename;
							String filePath = uploadFolder+"\\"+fileName;
							//将上传的文件保存到指定的文件夹下
							multipartFile.transferTo(new File(filePath));
							
							filePathMap.put(fileName, filePath);
						}
					}
				}
			}
		}
		
		return filePathMap;
	}
}
