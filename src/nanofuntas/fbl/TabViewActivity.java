package nanofuntas.fbl;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;

public class TabViewActivity extends FragmentActivity {
	private final boolean DEBUG = true;
	private final String TAG = "TabViewActivity";
	
    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (DEBUG) Log.d(TAG, "onCreate()");
    	    	
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tabview);
        
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        // For each of the sections in the app, add a tab to the action bar.
        actionBar.addTab(actionBar.newTab().setText("Team")
        		.setTabListener(new TabListener<TeamFragment>(this, "TeamFragment", TeamFragment.class) ));
        actionBar.addTab(actionBar.newTab().setText("Member")
        		.setTabListener(new TabListener<MemberFragment>(this, "MemberFragment", MemberFragment.class) ));
        //TODO: kakpple test
        actionBar.addTab(actionBar.newTab().setText("Tactics")
        		.setTabListener(new TabListener<MemberFragment>(this, "Tactics", MemberFragment.class) ));
        actionBar.addTab(actionBar.newTab().setText("Settings")
        		.setTabListener(new TabListener<SettingsFragment>(this, "SettingsFragment", SettingsFragment.class) ));
    }

    public class TabListener<T extends Fragment> implements ActionBar.TabListener {
    	private Fragment mFragment;
    	private final Activity mActivity;
    	private final String mTag;
    	private final Class<T> mClass;
    	
    	public TabListener(Activity activity, String tag, Class<T> cls){
    		mActivity = activity;
    		mTag = tag;
    		mClass = cls;
    	}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			if (DEBUG) Log.d(TAG, "onTabSelected()");
			
			//TODO: kakpple test
			if ( mTag.equals("Tactics") ) {
				Intent i = new Intent(TabViewActivity.this, TacticBoard.class);
				startActivity(i);
			} else {
				mFragment = Fragment.instantiate(mActivity, mClass.getName());
				getSupportFragmentManager().beginTransaction().replace(R.id.container, mFragment).commit();
			}
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {}
    	
    }
    
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getActionBar().setSelectedNavigationItem(
                    savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_SELECTED_NAVIGATION_ITEM,
                getActionBar().getSelectedNavigationIndex());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // remove the option menu in action bar
    	//getMenuInflater().inflate(R.menu.activity_tabview, menu);
        return true;
    }

}
