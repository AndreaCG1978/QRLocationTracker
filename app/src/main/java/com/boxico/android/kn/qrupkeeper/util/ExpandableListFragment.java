package com.boxico.android.kn.qrupkeeper.util;



import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

public class ExpandableListFragment extends FragmentActivity
        implements OnCreateContextMenuListener,
        ExpandableListView.OnChildClickListener, ExpandableListView.OnGroupCollapseListener,
        ExpandableListView.OnGroupExpandListener
{

    private static final int INTERNAL_EMPTY_ID = 0x00ff0001;

    final private Handler mHandler = new Handler();

    final private Runnable mRequestFocus = new Runnable() {
        public void run() {
            mList.focusableViewAvailable(mList);
        }
    };

    final private AdapterView.OnItemClickListener mOnClickListener
            = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            onListItemClick((ListView)parent, v, position, id);
        }
    };

    private ExpandableListAdapter mAdapter;
    private ExpandableListView mList;
    View mEmptyView;
    private TextView mStandardEmptyView;
    View mListContainer;
    private boolean mSetEmptyText;
    private boolean mListShown;
    private boolean mFinishedStart = false;

    public ExpandableListFragment() {
    }

    /**
     * Provide default implementation to return a simple list view.  Subclasses
     * can override to replace with their own layout.  If doing so, the
     * returned view hierarchy <em>must</em> have a ListView whose id
     * is {@link android.R.id#list android.R.id.list} and can optionally
     * have a sibling view id {@link android.R.id#empty android.R.id.empty}
     * that is to be shown when the list is empty.
     *
     * <p>If you are overriding this method with your own custom content,
     * consider including the standard layout {@link android.R.layout#list_content}
     * in your layout file, so that you continue to retain all of the standard
     * behavior of ListFragment.  In particular, this is currently the only
     * way to have the built-in indeterminant progress state be shown.
     */

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //FrameLayout root = new FrameLayout(getActivity());
        FrameLayout root = new FrameLayout(this);
        //TextView tv = new TextView(getActivity());
        TextView tv = new TextView(this);
        tv.setId(INTERNAL_EMPTY_ID);
        tv.setGravity(Gravity.CENTER);
        root.addView(tv, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    //    ListView lv = new ListView(getActivity());
        ListView lv = new ListView(this);
        lv.setId(android.R.id.list);
        lv.setDrawSelectorOnTop(false);
        root.addView(lv, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        ListView.LayoutParams lp = new ListView.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        root.setLayoutParams(lp);

        return root;
    }

    /*
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ensureList();
    }
*/

  /*  public void onDestroyView() {
        mHandler.removeCallbacks(mRequestFocus);
        mList = null;
        super.onDestroyView();
    }
*/
    /**
     * This method will be called when an item in the list is selected.
     * Subclasses should override. Subclasses can call
     * getListView().getItemAtPosition(position) if they need to access the
     * data associated with the selected item.
     *
     * @param l The ListView where the click happened
     * @param v The view that was clicked within the ListView
     * @param position The position of the view in the list
     * @param id The row id of the item that was clicked
     */
    private void onListItemClick(ListView l, View v, int position, long id) {
    }

    /**
     * Provide the cursor for the list view.
     */
    protected void setListAdapter(ExpandableListAdapter adapter) {
        boolean hadAdapter = mAdapter != null;
        mAdapter = adapter;
        if (mList != null) {
            mList.setAdapter(adapter);
            if (!mListShown && !hadAdapter) {
                // The list was hidden, and previously didn't have an
                // adapter.  It is now time to show it.
                View v = findViewById(android.R.id.list);
                if(v != null) {
                    setListShown(true, v.getWindowToken() != null);
                }
                //setListShown(true, false);
            }
        }
    }


    public void setSelection(int position) {
        ensureList();
        mList.setSelection(position);
    }

    public long getSelectedPosition() {
        ensureList();
        return mList.getSelectedPosition();
    }

    public long getSelectedId() {
        ensureList();
        return mList.getSelectedId();
    }

    public ExpandableListView getExpandableListView() {
        ensureList();
        return mList;
    }

    /**
     * The default content for a ListFragment has a TextView that can
     * be shown when the list is empty.  If you would like to have it
     * shown, call this method to supply the text it should use.
     */
    public void setEmptyText(CharSequence text) {
        ensureList();
        if (mStandardEmptyView == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
        mStandardEmptyView.setText(text);
        if (!mSetEmptyText) {
            mList.setEmptyView(mStandardEmptyView);
            mSetEmptyText = true;
        }
    }

    /**
     * Control whether the list is being displayed.  You can make it not
     * displayed if you are waiting for the initial data to show in it.  During
     * this time an indeterminant progress indicator will be shown instead.
     *
     * <p>Applications do not normally need to use this themselves.  The default
     * behavior of ListFragment is to start with the list not being shown, only
     * showing it once an adapter is given with
     * If the list at that point had not been shown, when it does get shown
     * it will be do without the user ever seeing the hidden state.
     *
     * @param shown If true, the list view is shown; if false, the progress
     * indicator.  The initial value is true.
     */
    public void setListShown(boolean shown) {
        setListShown(shown, true);
    }

    /**
     * Like {@link #setListShown(boolean)}, but no animation is used when
     * transitioning from the previous state.
     */
    public void setListShownNoAnimation(boolean shown) {
        setListShown(shown, false);
    }

    /**
     * Control whether the list is being displayed.  You can make it not
     * displayed if you are waiting for the initial data to show in it.  During
     * this time an indeterminant progress indicator will be shown instead.
     *
     * @param shown If true, the list view is shown; if false, the progress
     * indicator.  The initial value is true.
     * @param animate If true, an animation will be used to transition to the
     * new state.
     */
    private void setListShown(boolean shown, boolean animate) {
        ensureList();

  /*

        if (mListShown == shown) {
            return;
        }
        mListShown = shown;
        if (shown) {
            if (animate) {
                mListContainer.startAnimation(AnimationUtils.loadAnimation(
                        this, android.R.anim.fade_in));
            }
            mListContainer.setVisibility(View.VISIBLE);
        } else {
            if (animate) {
                mListContainer.startAnimation(AnimationUtils.loadAnimation(
                        this, android.R.anim.fade_out));
            }
            mListContainer.setVisibility(View.GONE);
        }*/
    }

    /**
     * Get the ListAdapter associated with this activity's ListView.
     */
    public ExpandableListAdapter getExpandableListAdapter() {
        return mAdapter;
    }

    private void ensureList() {

/*
        View root = findViewById(android.R.id.list);
        if (root == null) {
            throw new IllegalStateException("Content view not yet created");
        }
        if (root instanceof ExpandableListView) {
            mList = (ExpandableListView)root;
        } else {
            mStandardEmptyView = (TextView)root.findViewById(INTERNAL_EMPTY_ID);
            if (mStandardEmptyView == null) {
                mEmptyView = root.findViewById(android.R.id.empty);
            } else {
                mStandardEmptyView.setVisibility(View.GONE);
            }

//
            mListContainer = root.findViewById(android.R.id.list_container);
            View rawListView = root.findViewById(android.R.id.list);
            if (!(rawListView instanceof ExpandableListView)) {
                if (rawListView == null) {
                    throw new RuntimeException(
                            "Your content must have a ExpandableListView whose id attribute is " +
                                    "'android.R.id.list'");
                }
                throw new RuntimeException(
                        "Content has view with id attribute 'android.R.id.list' "
                                + "that is not a ExpandableListView class");
            }
            mList = (ExpandableListView)rawListView;
            if (mEmptyView != null) {
                mList.setEmptyView(mEmptyView);
            }
        }
        mListShown = true;
        mList.setOnItemClickListener(mOnClickListener);
        if (mAdapter != null) {
            setListAdapter(mAdapter);
        } else {
            // We are starting without an adapter, so assume we won't
            // have our data right away and start with the progress indicator.
            setListShown(false, false);
        }
        mHandler.post(mRequestFocus);
*/

    }

    @Override
    public void onGroupExpand(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onGroupCollapse(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onChildClick(ExpandableListView arg0, View arg1, int arg2,
                                int arg3, long arg4) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    }

    public void onContentChanged() {
        View emptyView = findViewById(android.R.id.empty);
        mList = findViewById(android.R.id.list);
        if (mList == null) {
            throw new RuntimeException(
                    "Your content must have a ExpandableListView whose id attribute is " +
                            "'android.R.id.list'");
        }
        if (emptyView != null) {
            mList.setEmptyView(emptyView);
        }
        mList.setOnChildClickListener(this);
        mList.setOnGroupExpandListener(this);
        mList.setOnGroupCollapseListener(this);

        if (mFinishedStart) {
            setListAdapter(mAdapter);
        }
        mFinishedStart = true;
    }
}
