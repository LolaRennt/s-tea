package org.sky.auto.mail;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**这个类暂时不提倡使用，有一些小问题*/
@Deprecated
/**这个类用来定义邮箱发送的附件的*/
public class MailAttachment {
	private List<String> file;
	private Multipart attachment;
	
	public MailAttachment() {
		file = new ArrayList<String>();
	}

	
	public void addAttachmentSource(String fname){
		file.add(fname);
	}
	
	public void deleteAttachementSource(String fname){
		 file.remove(fname);
	}
	
	public Multipart getAttachmentContext(){
		attachment = new MimeMultipart();
		for(int i=0;i<file.size();i++){
			MimeBodyPart mbp =new MimeBodyPart();
			FileDataSource filesource = new FileDataSource(file.get(i));
			try {
				mbp.setDataHandler(new DataHandler(filesource));
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				mbp.setFileName(MimeUtility.encodeWord(filesource.getName(), "Gb2312", null));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					attachment.addBodyPart(mbp);
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		//file.clear();
		return attachment;
	}
	
	public void clear(){
		file.clear();
	}
	
	public int size(){
		return file.size();
	}
}
