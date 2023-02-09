package com.KoreaIT.java.Am.container; // Dao가 공유할 수 있게 공간을 만들어주는 컨테이너창고같은 느낌?

/* service >  핵심인력 | DB > 데이터베이스 ArratList | Dao > 각Dao내부에 가지고 있는걸 공유하는? Memner
Dao
-MemberDao
 -members
-ArticleDao
-articles
*/

import com.KoreaIT.java.Am.dao.ArticleDao;
import com.KoreaIT.java.Am.dao.MemberDao;

public class container {
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
	}

}
