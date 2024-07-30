package com.example.demo;

import java.nio.charset.Charset;
import java.util.ArrayList;
//import java.net.http.HttpHeaders;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api.1.0")
public class Greet {
	
	@GetMapping
	@RequestMapping("/welcome")
	public ResponseEntity<String> message() {
		ResponseEntity<String> response= new ResponseEntity<>("OLA",HttpStatus.OK);
		return response;
	}
	//@GetMapping
	@GetMapping("/time")
	public ResponseEntity<String> getTime() {
		//ResponseEntity<String> response=new ResponseEntity<>(Calendar.getInstance().getTime()+"",HttpStatus.OK);
		return ResponseEntity.status(HttpStatus.OK).body(Calendar.getInstance().getTime()+"");
		//return response;
	}
	@GetMapping
	@RequestMapping("/{gender}")
	public String genderMessage(@PathVariable String gender) {
		if(gender.equals("Female"))
			return "Welcome Miss. ";
		else if(gender.equals("Male"))
			return "Welcome Mr. " ;
			else
				return "Welcome ";
		
	}
	
	@GetMapping
	@RequestMapping("/namegender/{gender}/{name}")
	public ResponseEntity<String> genderMessage2(@RequestHeader HttpHeaders headers, @PathVariable String gender,@PathVariable String name) {

		Set<String> set=headers.keySet();
		Iterator<String> keys=set.iterator();
		String keystr=null;
		while(keys.hasNext()) {
			keystr+=keys.next()+"\n";
			}
		
		//KeySet:
//		nullcontent-type
//		user-agent
//		accept
//		postman-token
//		host
//		accept-encoding
//		connection
//		content-length
		String keyValueStr=null;
		Set<Entry<String,List<String>>> entries= headers.entrySet();
		Iterator iter2=entries.iterator();
		while(iter2.hasNext()) {
			keyValueStr+=iter2.next()+"\n";
			
			
		}
		//return keyValueStr;
		HttpHeaders headers2=new HttpHeaders();
		headers2.setAcceptLanguageAsLocales(List.of(Locale.US, Locale.GERMANY));
		Charset charset=Charset.defaultCharset();
		List<Charset> list=new ArrayList<>();
		list.add(charset);
		
		//headers2.setAcceptCharset(null);
		headers2.setContentType(MediaType.TEXT_HTML);
		headers2.setContentLength(keyValueStr.length());
		headers2.set("UST","Gamma training");
		return ResponseEntity.status(HttpStatus.OK).headers(headers2).body(keyValueStr);
		
		
		//		if(gender.equals("Female"))
//			return "Welcome Miss. "+ name;
//		else if(gender.equals("Male"))
//			return "Welcome Mr. " +name;
//			else
//				return "Welcome "+name;
		
	}
	
	@PostMapping
	@RequestMapping("/namegender")
	public ResponseEntity<String> genderMessage3(@RequestBody Member member) {
		
		if(member.getGender().equals("Female"))
			return ResponseEntity.ok("Welcome Miss " +member.getName());
		else if(member.getGender().equals("Male"))
			return ResponseEntity.ok("Welcome Mr." +member.getName());
		else
			return ResponseEntity.ok("Welcome "+member.getName());
		
	}
		
		
	

}
