package com.medical.medicalexamination;

import com.medical.medicalexamination.common.EventHandler;
import com.medical.medicalexamination.common.EventMessage;
import com.medical.medicalexamination.common.Logs;
import com.medical.medicalexamination.common.Type;
import com.medical.medicalexamination.widget.FlipperView;

import android.app.Activity;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FlipperMenuHandler
{

	private FlipperView		flipperView			= null;
	private MenuID			menuId				= null;
	private RelativeLayout	loginMainLayout		= null;
	private RelativeLayout	historyMainLayout	= null;
	private RelativeLayout	calendarLayout		= null;
	private Handler			notifyHandler		= null;

	public class LoginData
	{
		public String	mstrName		= null;
		public String	mstrPassword	= null;
	}

	private class MenuID
	{
		public int	mnLoginId			= Type.INVALID;
		public int	mnHistoryId			= Type.INVALID;
		public int	mnCalendarId		= Type.INVALID;
		public int	mnSubscribeId		= Type.INVALID;
		public int	mnAccountAdd		= Type.INVALID;
		public int	mnForgetPassword	= Type.INVALID;
	}

	public FlipperMenuHandler(Activity activity)
	{
		super();
		init(activity);
	}

	private void init(Activity activity)
	{
		menuId = new MenuID();

		flipperView = (FlipperView) activity.findViewById(R.id.fliper_menu_option);
		if (null == flipperView)
		{
			Logs.showTrace("Flipper view is invalid");
			return;
		}

		menuId.mnLoginId = flipperView.addChild(R.layout.login);
		menuId.mnHistoryId = flipperView.addChild(R.layout.history);
		menuId.mnCalendarId = flipperView.addChild(R.layout.calendar);

		loginMainLayout = (RelativeLayout) flipperView.findViewById(R.id.login_main_layout);
		loginMainLayout.setOnTouchListener(mainLayoutTouch);
		loginHandle(activity);

		historyMainLayout = (RelativeLayout) flipperView.findViewById(R.id.history_main_layout);
		historyMainLayout.setOnTouchListener(mainLayoutTouch);
		historyHandler(activity);

		calendarLayout = (RelativeLayout) flipperView.findViewById(R.id.calendar_main_layout);
		calendarLayout.setOnTouchListener(mainLayoutTouch);

	}

	public void setNotifyHandler(Handler handler)
	{
		flipperView.setNotifyHandler(handler);
		notifyHandler = handler;
	}

	public void showLogin()
	{
		flipperView.showView(menuId.mnLoginId);
	}

	public void showHistory()
	{
		flipperView.showView(menuId.mnHistoryId);
	}

	public void showCalendar()
	{
		flipperView.showView(menuId.mnCalendarId);
	}

	private void loginHandle(final Activity activity)
	{

		TextView loginBtn = (TextView) loginMainLayout.findViewById(R.id.start_login);
		loginBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				EditText editText = (EditText) loginMainLayout.findViewById(R.id.login_name);
				String strName = editText.getText().toString();
				editText = (EditText) loginMainLayout.findViewById(R.id.login_password);
				String strPassword = editText.getText().toString();

				if (null != strName && 0 < strName.length() && null != strPassword && 0 < strPassword.length())
				{
					LoginData data = new LoginData();
					data.mstrName = strName;
					data.mstrPassword = strPassword;
					EventHandler.notify(notifyHandler, EventMessage.MSG_LOGIN, 0, 0, data);
					data = null;
					flipperView.close();
				}

			}
		});

	}

	private void historyHandler(final Activity activity)
	{

	}

	private OnTouchListener	mainLayoutTouch	= new OnTouchListener()
											{

												@Override
												public boolean onTouch(View v, MotionEvent event)
												{
													switch (event.getAction())
													{
													case MotionEvent.ACTION_DOWN:
														return true;
													}
													return false;
												}
											};
}
