package com.medical.medicalexamination;

import com.medical.medicalexamination.common.Logs;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MenuHandler
{
	private final int		ITEM_LOGIN	= 0;

	private ListView		listView	= null;
	private LayoutInflater	inflater	= null;

	public MenuHandler(Activity activity)
	{
		super();

		listView = (ListView) activity.findViewById(R.id.menu_list);
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		MenuAdapter adapter = new MenuAdapter();
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Logs.showTrace("Menu item selected position=" + position);

				switch (position)
				{
				case ITEM_LOGIN:

					break;
				}
			}
		});
	}

	public class MenuAdapter extends BaseAdapter
	{

		public MenuAdapter()
		{

		}

		@Override
		public int getCount()
		{
			return 1;
		}

		@Override
		public Object getItem(int position)
		{
			return position;
		}

		@Override
		public long getItemId(int position)
		{
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View view = inflater.inflate(R.layout.menu_item, null);

			TextView itemText = (TextView) view.findViewById(R.id.menu_item_text);
			ImageView itemImage = (ImageView) view.findViewById(R.id.menu_item_image);

			switch (position)
			{
			case ITEM_LOGIN:
				itemText.setText("Login");
				break;
			case 1:
				break;
			}

			return view;
		}

	}
}
