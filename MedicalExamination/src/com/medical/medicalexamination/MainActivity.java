package com.medical.medicalexamination;

import java.util.ArrayList;
import java.util.List;

import com.medical.medicalexamination.common.EventMessage;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends Activity
{
	private DrawerLayout		drawerLayout		= null;
	private ImageView			listMenuBtn			= null;
	private View				view1, view2, view3;
	private List<View>			viewList;
	private ViewPager			viewPager			= null;
	private MenuHandler			menuHandler			= null;
	private FlipperMenuHandler	flipperMenuHandler	= null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		/** init orientation*/
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		/** load content */
		setContentView(R.layout.activity_main);

		/** init Drawer Layout */
		initDrawerLayout();

		/** init viewpager */
		initViewPager();

		/** init menu */
		menuHandler = new MenuHandler(this, selfHandler);

		/** init flipper menu */
		flipperMenuHandler = new FlipperMenuHandler(this);
		flipperMenuHandler.setNotifyHandler(selfHandler);
	}

	private void initDrawerLayout()
	{
		listMenuBtn = (ImageView) this.findViewById(R.id.listMenuBtn);
		listMenuBtn.setOnClickListener(buttonClick);
		drawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		drawerLayout.setDrawerListener(new DrawerListener()
		{
			@Override
			public void onDrawerClosed(View arg0)
			{
				drawerHandler(false);
			}

			@Override
			public void onDrawerOpened(View arg0)
			{
				drawerHandler(true);
			}

			@Override
			public void onDrawerSlide(View arg0, float arg1)
			{

			}

			@Override
			public void onDrawerStateChanged(int arg0)
			{

			}
		});
	}

	private void drawerHandler(boolean bOpen)
	{
		if (bOpen)
		{
			listMenuBtn.setImageResource(R.drawable.list_click);
		}
		else
		{
			listMenuBtn.setImageResource(R.drawable.list_normal);
		}
	}

	private void initViewPager()
	{
		viewPager = (ViewPager) findViewById(R.id.viewpager);

		LayoutInflater lf = LayoutInflater.from(this);
		view1 = lf.inflate(R.layout.page01, null);
		view2 = lf.inflate(R.layout.page02, null);
		view3 = lf.inflate(R.layout.page03, null);

		viewList = new ArrayList<View>();
		viewList.add(view1);
		viewList.add(view2);
		viewList.add(view3);

		PagerAdapter pagerAdapter = new PagerAdapter()
		{
			@Override
			public int getCount()
			{
				return viewList.size();
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1)
			{
				return arg0 == arg1;
			}

			@Override
			public void destroyItem(ViewGroup container, int position, Object object)
			{
				container.removeView(viewList.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position)
			{
				container.addView(viewList.get(position));
				return viewList.get(position);
			}

		};

		viewPager.setAdapter(pagerAdapter);

		/** init focuse */
		view1.findViewById(R.id.editTextName).requestFocus();

	}

	private void handleButtonClick(View view)
	{
		if (view.getId() == R.id.listMenuBtn)
		{
			if (drawerLayout.isDrawerOpen(Gravity.LEFT))
			{
				drawerLayout.closeDrawers();
			}
			else
			{
				drawerLayout.openDrawer(Gravity.LEFT);
			}
		}
	}

	private OnClickListener	buttonClick	= new OnClickListener()
										{
											@Override
											public void onClick(View view)
											{
												handleButtonClick(view);
											}
										};

	private Handler			selfHandler	= new Handler()
										{

											@Override
											public void handleMessage(Message msg)
											{
												switch (msg.what)
												{
												case EventMessage.MSG_LOGIN:
													break;
												case EventMessage.MSG_SHOW_LOGIN:
													flipperMenuHandler.showLogin();
													break;
												case EventMessage.MSG_SHOW_HISTORY:
													flipperMenuHandler.showCalendar();
													break;
												}
											}

										};

}
