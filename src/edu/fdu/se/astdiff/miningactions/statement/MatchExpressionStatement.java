package edu.fdu.se.astdiff.miningactions.statement;

import com.github.gumtreediff.actions.model.Action;

import com.github.gumtreediff.tree.Tree;
import com.github.javaparser.Range;
import edu.fdu.se.astdiff.miningactions.util.DefaultDownUpTraversal;
import edu.fdu.se.astdiff.miningactions.util.DefaultUpDownTraversal;
import edu.fdu.se.astdiff.miningactions.bean.MiningActionData;
import edu.fdu.se.astdiff.miningactions.bean.ChangePacket;
import edu.fdu.se.astdiff.miningactions.util.AstRelations;
import edu.fdu.se.astdiff.miningoperationbean.ClusteredActionBean;
import edu.fdu.se.astdiff.miningoperationbean.OperationTypeConstants;
import edu.fdu.se.astdiff.miningoperationbean.model.ChangeEntity;
import edu.fdu.se.astdiff.miningoperationbean.statementplus.ExpressionChangeEntity;

import java.util.ArrayList;
import java.util.List;

public class MatchExpressionStatement {


	public static void matchExpression(MiningActionData fp, Action a) {
		ChangePacket changePacket = new ChangePacket();
		List<Action> subActions = new ArrayList<>();
		changePacket.setOperationType(OperationTypeConstants.getEditTypeIntCode(a));
		changePacket.setOperationEntity(OperationTypeConstants.ENTITY_STATEMENT_TYPE_I);
		DefaultUpDownTraversal.traverseTypeIStatements(a,subActions,changePacket);
		fp.setActionTraversedMap(subActions);
		Range range = AstRelations.getRangeOfAstNode(a);
		ClusteredActionBean mBean = new ClusteredActionBean(ClusteredActionBean.TRAVERSE_UP_DOWN,a,subActions,changePacket,range);
		ExpressionChangeEntity code = new ExpressionChangeEntity(mBean);
		fp.addOneChangeEntity(code);
	}


	public static void matchExpressionChangeNewEntity(MiningActionData fp, Action a, Tree queryFather, Tree traverseFather) {
		ChangePacket changePacket = new ChangePacket();
		List<Action> subActions = new ArrayList<>();
		changePacket.setOperationEntity(OperationTypeConstants.ENTITY_STATEMENT_TYPE_I);
		DefaultDownUpTraversal.traverseFatherNodeGetSameNodeActions(traverseFather,subActions,changePacket);
		fp.setActionTraversedMap(subActions);
		Range range = AstRelations.getRangeOfAstNode(a);
		ClusteredActionBean mBean = new ClusteredActionBean(ClusteredActionBean.TRAVERSE_DOWN_UP,a,subActions,changePacket,range,queryFather);
		ExpressionChangeEntity code = new ExpressionChangeEntity(mBean);
		fp.addOneChangeEntity(code);
	}

	public static void matchExpressionChangeCurrEntity(MiningActionData fp, Action a, ChangeEntity changeEntity,Tree traverseFather){
		ChangePacket changePacket = changeEntity.clusteredActionBean.changePacket;
		List<Action> actions = changeEntity.clusteredActionBean.actions;
		List<Action> newActions = new ArrayList<>();
		DefaultDownUpTraversal.traverseFatherNodeGetSameNodeActions(traverseFather,newActions,changePacket);
		for(Action tmp:newActions){
			if(fp.mGeneratingActionsData.getAllActionMap().get(tmp)==1){
				continue;
			}
			actions.add(tmp);
		}
		fp.setActionTraversedMap(newActions);

	}


}
