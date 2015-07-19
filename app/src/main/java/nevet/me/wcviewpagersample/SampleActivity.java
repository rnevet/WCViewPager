package nevet.me.wcviewpagersample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class SampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        ArrayList<String> stringArrayList = new ArrayList<String>();
        stringArrayList.add(getString(R.string.lorem_short));
        stringArrayList.add(getString(R.string.lorem_medium));
        stringArrayList.add(getString(R.string.lorem_long));
        stringArrayList.add(getString(R.string.lorem_medium));
        stringArrayList.add(getString(R.string.lorem_short));

        SamplePagerAdapter adapter = new SamplePagerAdapter(stringArrayList);
        ((ViewPager) findViewById(R.id.viewpager)).setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class SamplePagerAdapter extends PagerAdapter {

        private final ArrayList<String> strings;

        public SamplePagerAdapter(ArrayList<String> strings) {
            super();
            this.strings = strings;
        }

        @Override
        public int getCount() {
            return strings.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(container.getContext(), android.R.layout.test_list_item, null);
            ((TextView)view.findViewById(android.R.id.text1)).setText(strings.get(position));
            container.addView(view);

            // set Random background
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            view.setBackgroundColor(color);

            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
