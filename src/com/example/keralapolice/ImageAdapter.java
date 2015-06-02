package com.example.keralapolice;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageAdapter extends PagerAdapter {
	Context context;
	private int[] GalImages = new int[] {
	R.drawable.ic_communities,
	R.drawable.ic_home,
	R.drawable.ic_whats_hot
	};
	ImageAdapter(Context context){
	this.context=context;
	}
	public ImageAdapter(DetailFragment detailFragment) {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getCount() {
	return GalImages.length;
	}
	 
	@Override
	public boolean isViewFromObject(View view, Object object) {
	return view == ((ImageView) object);
	}
	 
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
	ImageView imageView = new ImageView(context);
	//int padding = context.getResources().getDimensionPixelSize(R.dimen.padding_medium);
	//imageView.setPadding(padding, padding, padding, padding);
	imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
	imageView.setImageResource(GalImages[position]);
	container.addView(imageView, 0);
	return imageView;
	}
	 
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
	container.removeView((ImageView) object);
	}
	}

