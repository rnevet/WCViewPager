## WCViewPager
Android ViewPager that respects height of wrap_content. 

The WrapContentViewPager is an *almost* an in-place replacement for a [android.support.v4.view.ViewPager](http://developer.android.com/reference/android/support/v4/view/ViewPager.html).
When used with wrap_content height the WrapContentViewPager will resize to the height of the selected child view and smoothly change its height when ViewPager is Scrolled.

Solution was inspired by answers to this StackOverflow question: http://stackoverflow.com/questions/8394681/android-i-am-unable-to-have-viewpager-wrap-content

**UPDATE BREAKING CHANGE! Support24: As of support library24 there has been a change in PagerAdapter preventing the posibility to wrap it, in order to use WrapContentViewPager your PagerAdapter Implementation needs to implement the [ObjectAtPositionInterface](https://github.com/rnevet/WCViewPager/blob/master/wcviewpager/src/main/java/nevet/me/wcviewpager/ObjectAtPositionInterface.java) or extend the [ObjectAtPositionPagerAdapter](https://github.com/rnevet/WCViewPager/blob/master/wcviewpager/src/main/java/nevet/me/wcviewpager/ObjectAtPositionPagerAdapter.java).**

**This is a work in progress, so there still might be bugs and edge cases not covered.
If you find a bug and open an Issue, please provide instructions how to reproduce it.**

### Including
At the moment the easiest way to include the library is to copy the [classes](https://github.com/rnevet/WCViewPager/blob/master/wcviewpager/src/main/java/nevet/me/wcviewpager) into your project.

The implementation relies the v4 support library ViewPager.

### Use
Just like a ViewPager, either in an XML or Code.
**UPDATE Support24:
PagerAdapter needs to implement [ObjectAtPositionInterface](https://github.com/rnevet/WCViewPager/blob/master/wcviewpager/src/main/java/nevet/me/wcviewpager/ObjectAtPositionInterface.java)**

#### Sample project
A simple Sample (using TextViews and ImageViews) project is available for reference under [app](https://github.com/rnevet/WCViewPager/tree/master/app).
You can install the [APK](https://github.com/rnevet/WCViewPager/blob/master/app/wcviewpager-sample.apk?raw=true) to see it in action. 

### License
This project is licensed under the terms of the [MIT license](https://github.com/rnevet/WCViewPager/blob/master/LICENSE).
