package com.example.keralapolice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class SeniorityFrgament extends Fragment {
	 ExpandableListView expandableListView;
	    ExpandableListAdapter expandableListAdapter;
	    List expandableListTitle;
	    HashMap expandableListDetail;
		final Context context = getActivity();
	 @Override
	    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle args) {
	        View view = inflater.inflate(R.layout.activity_seniority, container, false);
	        expandableListView = (ExpandableListView)view.findViewById(R.id.expandableListView1);
	        expandableListDetail =getData();
	        expandableListTitle = new ArrayList(expandableListDetail.keySet());
	        expandableListAdapter = new com.example.keralapolice.ExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
	        expandableListView.setAdapter(expandableListAdapter);

	        expandableListView.setOnGroupExpandListener(new OnGroupExpandListener() {

	            @Override
	            public void onGroupExpand(int groupPosition) {
	          	  
	          	
	                Toast.makeText(getActivity(),
	                        expandableListTitle.get(groupPosition) + " List Expanded.",
	                        Toast.LENGTH_SHORT).show();
	            }


	        });
	  		 

	        expandableListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

	            @Override
	            public void onGroupCollapse(int groupPosition) {
	                Toast.makeText(getActivity(),
	                        expandableListTitle.get(groupPosition) + " List Collapsed.",
	                        Toast.LENGTH_SHORT).show();

	            }
	        });

	        expandableListView.setOnChildClickListener(new OnChildClickListener() {
	            @Override
	            public boolean onChildClick(ExpandableListView parent, View v,
	                                        int groupPosition, int childPosition, long id) {
	            	
	            	String district= expandableListTitle.get(groupPosition).toString();
	            	 Toast.makeText(getActivity(), district, Toast.LENGTH_LONG).show();
	            	 String rank = ((TextView)v.findViewById(R.id.childtxt)).getText()
                           .toString();
	                   Toast.makeText(getActivity(), rank, Toast.LENGTH_LONG).show();
	                   Intent i=new Intent(getActivity(),RankList.class);
	                   i.putExtra("key1", district);
	                   i.putExtra("key2", rank);
	                   startActivity(i);
	                return false;
            
	        }  });	
	        
	 
	        return view;
	    }
	  public static HashMap getData() {
	        HashMap expandableListDetail = new HashMap();

	        List p = new ArrayList();
	        p.add("DGP");
	        p.add("IGP");
	        p.add("DIG");
	        p.add("SP");
	        p.add("ACP");
	        p.add("IGP");
	        p.add("ASP");
	        p.add("CI");
	        p.add("SI");
	        p.add("HC");
	        p.add("PC");
	      
	     
	        
	        expandableListDetail.put("MALABAR RANGE", p);
	        expandableListDetail.put("ERNAMKULAUM RANGE", p);
	        expandableListDetail.put("TRIVANDRUM RANGE", p);
	      

	        return expandableListDetail;
	    }

}
