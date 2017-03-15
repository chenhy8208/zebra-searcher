package com.hongru.common.mongo;

import com.hongru.common.Constant;
import com.hongru.common.model.SpiderRecord;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MongoUtil {

	public static void insertVisitRecord(SpiderRecord spiderRecord)
	{
		if (!hasUniqueIndex) {
			//设置唯一索引
			DBObject index = new BasicDBObject();
			index.put("url", 1);
			index.put("unique", true);
			MongoManager.
					getCollection(Constant.WEBSITE_COLLECTION).createIndex(index);

			//时间倒叙
			DBObject dateIndex = new BasicDBObject();
			dateIndex.put("createDate", -1);
			MongoManager.
					getCollection(Constant.WEBSITE_COLLECTION).createIndex(dateIndex);

			hasUniqueIndex = true;
		}

		DBObject object = new BasicDBObject();
		object.put("url", spiderRecord.getUrl());
		object.put("createDate", spiderRecord.getCreateDate());
		object.put("level", spiderRecord.getLevel());
		object.put("perhapsReviewDate", spiderRecord.getPerhapsReviewDate());
		MongoManager.insert(Constant.WEBSITE_COLLECTION, object);
	}

	private static boolean hasUniqueIndex = false;
}
