package com.application.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.application.imageholders.ImageHolder;
import com.application.imageholders.ImageHolderBuilder;
import com.application.models.BeachModel;
import com.application.models.Whether;
import com.application.models.Whether.FlagType;
import com.application.models.Whether.Wave;

public class StaticObjectHalper {

	public static List<BeachModel> createStaticBeachList(){
		List<BeachModel> list = new ArrayList<BeachModel>();
		Whether w = new Whether();
		w.setDescription("12 - 20");

		Wave wave = new Wave();
		FlagType flag = new FlagType();


		flag.setDescription("דגל אדום");
		wave.setDescription("1-2 מטר");

		w.setWave(wave);
		w.setFlag(flag);
		BeachModel palmahim = getBeachModel("111","פלמחים",w,4,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGdO2rmVu0MRcqJvrEK43PBrOB8fKm9WGbkjqc21af2mnOgmV2",100,10,"הגן הלאומי פלמחים (או חולות פלמחים) משתרע משפך נחל שורק עד גבול קיבוץ פלמחים על פני 221 דונם, ושייך למועצה אזורית גן רווה. אורך החוף הוא 8.8 קילומטר, וכולל בתי גידול ייחודיים. אזור הטלה של צבי ים, וצמחיה ובעלי חיים מסוגים שונים.",400,300,15);
		BeachModel reshon = getBeachModel("112","חוף ראשון",w,4,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGdO2rmVu0MRcqJvrEK43PBrOB8fKm9WGbkjqc21af2mnOgmV2",300,22,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",430,60,15);
		BeachModel gordon = getBeachModel("113","חוף בוגרשוב",w,4,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGdO2rmVu0MRcqJvrEK43PBrOB8fKm9WGbkjqc21af2mnOgmV2",150,97,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",100,620,0);
		BeachModel habonim = getBeachModel("114","חוף מציצים",w,4,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGdO2rmVu0MRcqJvrEK43PBrOB8fKm9WGbkjqc21af2mnOgmV2",220,1000,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",400,600,20);
		BeachModel mecmoret = getBeachModel("115","חוף הבונים",w,4,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGdO2rmVu0MRcqJvrEK43PBrOB8fKm9WGbkjqc21af2mnOgmV2",180,103,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",4,200,15);
		BeachModel avram = getBeachModel("116","החוף השקט",w,4,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGdO2rmVu0MRcqJvrEK43PBrOB8fKm9WGbkjqc21af2mnOgmV2",400,15,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",432,100,25);
		BeachModel dado = getBeachModel("117","חוף אברהם",w,4,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGdO2rmVu0MRcqJvrEK43PBrOB8fKm9WGbkjqc21af2mnOgmV2",300,10,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",40,60,0);
		BeachModel hashaket = getBeachModel("118","חוף לבנון",w,4,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGdO2rmVu0MRcqJvrEK43PBrOB8fKm9WGbkjqc21af2mnOgmV2",55,30,"חוף פלמחי�? - קיבוץ פלמחי�? חוף טיבעי לל�? מסעדות מיוחדות. קיימת מסעדה �?חת כניסה",100,230,0);

		list.add(palmahim);list.add(reshon);
		list.add(gordon);list.add(habonim);
		list.add(mecmoret);list.add(avram);
		list.add(dado);list.add(hashaket);

		return list;
	}

	private static BeachModel getBeachModel(String id, String name,Whether whether,
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
		model.setWhether(whether);
		model.setAttractionsDescription("בתי קפה, פאבים, סירות מנועת טיולים מאורגנים ועוד");


		Random r = new Random();
		model.setScore(4);


		return model;
	}

	public static ArrayList<ImageHolder> getStaticUsers(){
		ArrayList<ImageHolder> holders = new ArrayList<ImageHolder>();
		for(int i = 0 ; i< 80; i++){
			ImageHolder holder = new ImageHolder("629860287121815", "Elad kariti", "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xaf1/v/t1.0-1/c0.0.320.320/p320x320/10273794_582201381887706_2165682812399660997_n.jpg?oh=146e4a44739de6f2a1e99fa822dfee31&oe=55E0DBD4&__gda__=1436314007_b0c7ec228c153691f6d9fda2f03a3726");
			holder.addExtension(ImageHolderBuilder.IS_USER_FRIEND,i% 10 ==0 ? "true" : "false");
			holders.add(holder);
		}
		return holders;

	}

}
