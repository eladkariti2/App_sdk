package com.application.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.application.models.BeachModel;
import com.application.models.GenericModel;

public class StaticObjectHalper {
	
	public static List<BeachModel> createStaticBeachList(){
		List<BeachModel> list = new ArrayList<BeachModel>();
		
		BeachModel palmahim = getBeachModel("111","פלמחים",4,"http://i.imgur.com/DvpvklR.png",100,10,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",400,300,15);
		BeachModel reshon = getBeachModel("112","חוף ראשון",4,"http://i.imgur.com/DvpvklR.png",300,22,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",430,60,15);
		BeachModel gordon = getBeachModel("113","nmhmho",4,"http://images.applicaster.com/accounts/32/broadcasters/1/vod_items/2163581/image_assets/3563203/original.jpg?1405656157",150,97,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",100,620,0);
		BeachModel habonim = getBeachModel("114","פלמחי�?",4,"http://images.applicaster.com/accounts/32/broadcasters/1/vod_items/2163581/image_assets/3563203/original.jpg?1405656157",220,1000,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",400,600,20);
		BeachModel mecmoret = getBeachModel("115","פלמחי�?",4,"",180,103,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",4,200,15);
		BeachModel avram = getBeachModel("116","פלמחי�?",4,"http://images.applicaster.com/accounts/32/broadcasters/1/vod_items/2163581/image_assets/3563203/original.jpg?1405656157",400,15,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",432,100,25);
		BeachModel dado = getBeachModel("117","פלמחי�?",4,"",300,10,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",40,60,0);
		BeachModel hashaket = getBeachModel("118","פלמחי�?",4,"http://images.applicaster.com/accounts/32/broadcasters/1/vod_items/2163581/image_assets/3563203/original.jpg?1405656157",55,30,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",100,230,0);
		
		list.add(palmahim);list.add(reshon);
		list.add(gordon);list.add(habonim);
		list.add(mecmoret);list.add(avram);
		list.add(dado);list.add(hashaket);
		
		return list;
	}

	private static BeachModel getBeachModel(String id, String name,
			int score, String image, int likes, int comments,
			String desciption,int mensNum,int WomenNum,double entryFee) {
	
		BeachModel model = new BeachModel();
		model.setid(id);
		model.setName(name);
		model.setDescription(desciption);
		model.setEntryFee(entryFee);
		model.setLikes(likes);
		model.setMensNumber(mensNum);
		model.setWomenNumber(WomenNum);
		model.setComments(comments);
		model.setLikes(likes);
		model.setImage(image);
	
		
		Random r = new Random();
		model.setScore(4);
		
		
		return model;
	}

}
