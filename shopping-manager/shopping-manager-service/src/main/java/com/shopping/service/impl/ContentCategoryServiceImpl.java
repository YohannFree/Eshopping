package com.shopping.service.impl;

import com.shopping.common.pojo.EUTreeNode;
import com.shopping.mapper.TbContentCategoryMapper;
import com.shopping.pojo.TbContentCategory;
import com.shopping.pojo.TbContentCategoryExample;
import com.shopping.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/16.
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EUTreeNode> getCategoryList(long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        List<EUTreeNode> resultList = new ArrayList<>();
        for (TbContentCategory tbcontentCategory : list) {
            EUTreeNode node = new EUTreeNode();
            node.setId(tbcontentCategory.getId());
            node.setText(tbcontentCategory.getName());
            node.setState(tbcontentCategory.getIsParent() ? "closed" : "open");
            resultList.add(node);
        }
        return resultList;
    }
}