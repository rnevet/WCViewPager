## WCViewPager
Android ViewPager that respects height of wrap_content. 

The WrapContentViewPager is an in-place replacement for a [android.support.v4.view.ViewPager](http://developer.android.com/reference/android/support/v4/view/ViewPager.html).
When used with wrap_content height the WrapContentViewPager will resize to the height of the selected child view and smoothly change its height when ViewPager is Scrolled.

Solution was inspired by answers to this StackOverflow question: http://stackoverflow.com/questions/8394681/android-i-am-unable-to-have-viewpager-wrap-content

**This is a work in progress, so there still might be bugs and edge cases not covered.**

### Including
At the moment the easiest way to include the library is to copy [WrapConentViewPager](https://github.com/rnevet/WCViewPager/blob/master/wcviewpager/src/main/java/nevet/me/wcviewpager/WrapContentViewPager.java) Class into your project.
The class relies the v4 support library ViewPager.

### Use
Just like a ViewPager, either in an XML or Code.
A simple Sample project is available for reference.

### License
This project is licensed under the terms of the [MIT license](https://github.com/rnevet/WCViewPager/blob/master/LICENSE).
