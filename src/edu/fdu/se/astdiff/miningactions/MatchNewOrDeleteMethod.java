package edu.fdu.se.astdiff.miningactions;

import java.util.ArrayList;
import java.util.List;

import com.github.gumtreediff.actions.model.Action;

import edu.fdu.se.astdiff.miningoperationbean.HighLevelOperationBean;
import edu.fdu.se.gumtree.MyTreeUtil;

public class MatchNewOrDeleteMethod {
	
	/**
	 * level III insert 操作中的新增方法 ok
	 * 
	 * 
	 * @param a
	 * @return
	 */
	public static HighLevelOperationBean matchNewOrDeleteMethod(FindPatternData fp, Action a, String nodeType) {
		String operationEntity = "NEWORDELETEMETHOD";
		List<Action> subActions = new ArrayList<Action>();
		int status = MyTreeUtil.traverseNodeGetAllEditActions(a, subActions);
//		if (status ==MyTreeUtil.TYPE1) {
//			result = "[PATTREN] Add method";
//		} else if(status == MyTreeUtil.TYPE2){
//
//		} else {
//			System.err.println("What? Not New Method?");
//		}
		fp.setActionTraversedMap(subActions);

		HighLevelOperationBean mHighLevelOperationBean = new HighLevelOperationBean(
				a,nodeType,subActions,status,operationEntity,null,null);
		return mHighLevelOperationBean;
	}

}
