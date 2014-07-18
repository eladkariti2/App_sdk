package com.application.helper;

import java.util.ArrayList;
import java.util.List;

import com.application.models.BeachModel;
import com.application.models.GenericModel;

public class StaticObjectHalper {
	
	public static List<GenericModel> createStaticBeachList(){
		List<GenericModel> list = new ArrayList<GenericModel>();
		
		GenericModel palmahim = getBeachModel("111","פלמחי�?",4,"",100,10,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",400,300,15);
		GenericModel reshon = getBeachModel("112","חוף ר�?שון",4,"",300,22,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",430,60,15);
		GenericModel gordon = getBeachModel("113","פלמחי�?",4,"",150,97,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",100,620,0);
		GenericModel habonim = getBeachModel("114","פלמחי�?",4,"",220,1000,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",400,600,20);
		GenericModel mecmoret = getBeachModel("115","פלמחי�?",4,"",180,103,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",4,200,15);
		GenericModel avram = getBeachModel("116","פלמחי�?",4,"",400,15,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",432,100,25);
		GenericModel dado = getBeachModel("117","פלמחי�?",4,"",300,10,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",40,60,0);
		GenericModel hashaket = getBeachModel("118","פלמחי�?",4,"",55,30,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",100,230,0);
		
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
		model.setmID(id);
		model.setmName(name);
		model.setmDescription(desciption);
		model.setmEntryFee(entryFee);
		model.setmLikes(likes);
		model.setmMensNumber(mensNum);
		model.setmWomenNumber(WomenNum);
		model.setmComments(comments);
		model.setmLikes(likes);
		model.setmImage(image);
		
		return model;
	}

}
