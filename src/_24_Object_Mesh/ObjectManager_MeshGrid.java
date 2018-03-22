package _24_Object_Mesh;

import _24_Object_Mesh.Object_31_MeshNode;
import java.util.HashMap;
import java.util.Map;

//************************************************************************//
/**
*	シングルトン
*	accessはget_instance()から行う。
*/
//************************************************************************//
public class ObjectManager_MeshGrid
{
	private static final ObjectManager_MeshGrid m_instance = new ObjectManager_MeshGrid();
	private final Map<Long, Object_31_MeshNode> m_map_gid_obj = new HashMap<>();
	
	private long	m_current_id = 0L;
	

	private ObjectManager_MeshGrid() {
//		this.m_arry_meshgrid = new ArrayList<>();
	}
	//************************************************************************//
	/**
	*	シングルトンへのアクセス
	 * @return 
	*/
	//************************************************************************//
	static public ObjectManager_MeshGrid get_instance()	{return m_instance;}
	
	//************************************************************************//
	/**
	*	add
	* @return 
	*/
	//************************************************************************//
	public synchronized Long add_new_grid(Object_31_MeshNode mg)
	{
		for(long nc=0; nc<Long.MAX_VALUE; nc++)
		{
			//新しいIDを生成
			Long nid = this.ask_new_id();

			//登録できた。
			if(this.registor(mg, nid) == true){
				return nid;
			}
			//登録できない。
			else{
				//idがマイナスの値。つまり上限越えで生成できない。
				if(nid < 0)	return nid;
			}
		}

		//節点idの上限数ループを回しても解決できない。
		return -1L;
	}
	//************************************************************************//
	/**
	*	新しいidを発行
	* @return 
	*/
	//************************************************************************//
	public synchronized Long ask_new_id()
	{
		long id_buf = ++ m_current_id;
		
		//上限以下の場合
		if(id_buf <= Long.MAX_VALUE)
		{
			return id_buf;
		}
		//idの上限を超える場合
		else
		{
			//空いている番号がないか探す
			for(long nc=1; nc<Long.MAX_VALUE; nc++)
			{
				//空きを見つけた場合
				if(this.is_exist(nc) == false)
				{
					m_current_id = nc;
					return nc;
				}
			}			
		}
		
		//全idが使用されているため、もう節点を作れない。
		return -1L;
	}
	
	

//	//************************************************************************//
//	/**
//	*	add
//	* @return 
//	*/
//	//************************************************************************//
//	public Long add_meshgrid(Object_MeshGrid mg)
//	{
//		//新しいIDを生成
//		Long gid = this.ask_new_id();
//		
//
//		
//		return gid;
//	}
	//************************************************************************//
	/**
	*	登録
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	synchronized public boolean registor(Object_31_MeshNode mg, long nid)
	{
		//重複がある場合
		if(this.is_exist(nid) == true)
		{
			//同じ節点オブジェの場合
			if(this.m_map_gid_obj.get(nid) == mg)
			{
				//何もしなくても問題ない。trueを返す。
				return true;
			}
			else
			{
				//重複しているので登録できない。
				return false;
			}
		}
		//重複がない。登録する。
		else
		{
			//登録
			this.m_map_gid_obj.put(nid, mg);
			return true;
		}
	}

	//************************************************************************//
	/**
	*	節点IDの重複をチェック
	*
	*	@param
	*	@return	true 重複していないとき
	*	@version
	*/
	//************************************************************************//
	synchronized public boolean is_exist(Long id)
	{
		return this.m_map_gid_obj.containsKey(id);
	}
	
	/***************************************************************************
	 * 
	 * 検索
	 * 
	 */
	public Object_31_MeshNode search(long id)
	{
		return this.m_map_gid_obj.get(id);
	}
}
