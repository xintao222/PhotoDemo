package com.example.localphotodemo.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.localphotodemo.R;
import com.example.localphotodemo.bean.AlbumInfo;
import com.example.localphotodemo.imageaware.RotateImageViewAware;
import com.example.localphotodemo.util.ThumbnailsUtil;
import com.example.localphotodemo.util.UniversalImageLoadTool;

/**
 * 相册适配器
 * @author GuiLin
 */
public class PhotoFolderAdapter extends BaseAdapter {
	
	private LayoutInflater mInflater;
	private List<AlbumInfo> list;
	private ViewHolder viewHolder;
	
	public PhotoFolderAdapter(Context context,List<AlbumInfo> list){
		mInflater = LayoutInflater.from(context);
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_photofolder, null);
			viewHolder.image = (ImageView)convertView.findViewById(R.id.imageView);
			viewHolder.text = (TextView)convertView.findViewById(R.id.info);
			viewHolder.num = (TextView)convertView.findViewById(R.id.num);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final AlbumInfo albumInfo = list.get(position);
		UniversalImageLoadTool.disPlay(ThumbnailsUtil.MapgetHashValue(albumInfo.getImage_id(),albumInfo.getFilePath()), 
				new RotateImageViewAware(viewHolder.image,albumInfo.getAbsolutePath()), R.drawable.common_defalt_bg);
		viewHolder.text.setText(albumInfo.getAlbumName());
		viewHolder.num.setText("("+list.get(position).getList().size()+"张)");
		return convertView;
	}
	
	public class ViewHolder{
		public ImageView image;
		public TextView text;
		public TextView num;
	}
}
