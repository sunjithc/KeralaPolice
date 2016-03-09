# KeralaPolice

<android.support.v4.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/ldrawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- Framelayout to display Fragments -->
    <FrameLayout
        android:id="@+id/frame_container"
        android:layout_width="match_parent"
        android:layout_height="match_parent" >
<ScrollView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:fitsSystemWindows="true"
    android:background="#f0dd9f">

    <RelativeLayout
        android:id="@+id/relativelayout1"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:weightSum="1">

        <!--<RelativeLayout-->
            <!--android:id="@+id/rel1"-->
            <!--android:layout_width="fill_parent"-->
            <!--android:layout_height="55dp"-->
            <!--android:background="@drawable/top">-->


            <!--<ImageView-->
                <!--android:id="@+id/btn_home"-->
                <!--android:layout_width="wrap_content"-->
                <!--android:layout_height="wrap_content"-->
                <!--android:layout_alignParentLeft="true"-->
                <!--android:layout_centerVertical="true"-->
                <!--android:layout_marginLeft="10dp"-->
                <!--android:src="@drawable/home_icon" />-->

            <!--<ImageView-->
                <!--android:id="@+id/btn_cart"-->
                <!--android:layout_width="wrap_content"-->
                <!--android:layout_height="wrap_content"-->
                <!--android:layout_centerVertical="true"-->
                <!--android:layout_marginEnd="34dp"-->
                <!--android:layout_marginRight="34dp"-->
                <!--android:layout_toLeftOf="@+id/btn_wishlist"-->
                <!--android:layout_toStartOf="@+id/btn_wishlist"-->
                <!--android:src="@drawable/cart" />-->

            <!--<ImageView-->
                <!--android:id="@+id/btn_wishlist"-->
                <!--android:layout_width="wrap_content"-->
                <!--android:layout_height="wrap_content"-->
                <!--android:layout_alignParentEnd="true"-->
                <!--android:layout_alignParentRight="true"-->
                <!--android:layout_alignTop="@+id/btn_cart"-->
                <!--android:layout_marginRight="10dp"-->
                <!--android:src="@drawable/wishlist" />-->


        <!--</RelativeLayout>-->

        <RelativeLayout
            android:orientation="vertical"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:paddingTop="56dp"
            android:paddingLeft="24dp"
            android:paddingRight="24dp">

            <ImageView android:src="@drawable/logo_food"
                android:id="@+id/img_logo"
                android:layout_width="wrap_content"
                android:layout_height="72dp"
                android:layout_marginBottom="24dp"
                android:layout_gravity="center_horizontal" />
            <TextView
                android:id="@+id/cusname"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"

                android:layout_below="@id/img_logo"
              android:layout_marginBottom="24dp"
                android:drawableLeft="@drawable/user"
                android:text="Medium Text"
                android:gravity="left"
                android:textAppearance="?android:attr/textAppearanceMedium"
                android:layout_marginLeft="20dp"
                android:layout_marginRight="30dp"/>

            <TextView
                android:id="@+id/cusphone"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:drawableLeft="@drawable/phone"
                android:layout_marginBottom="24dp"
                android:singleLine="true"
                android:text="Medium Text"
                android:gravity="left"
                android:textAppearance="?android:attr/textAppearanceSmall"
                android:layout_below="@+id/cusname"

                android:layout_marginTop="15dp"
                android:layout_marginLeft="20dp"
                android:layout_marginRight="30dp"/>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:id="@+id/cusadd"
                android:drawableLeft="@drawable/address"
                android:layout_below="@+id/cusphone"
                android:layout_marginTop="15dp"
                android:layout_marginLeft="20dp"
                android:layout_marginRight="30dp"/>

            EditText
            android:id="@+id/modify_address"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_alignParentLeft="true"
            android:layout_alignParentStart="true"
            android:layout_below="@+id/cusadd"
            android:layout_marginLeft="20dp"
            android:layout_marginRight="30dp"
            android:layout_marginTop="26dp"
            android:clickable="false"
            android:cursorVisible="false"
            android:ems="10"
            android:focusable="false"
            android:focusableInTouchMode="false"
            android:gravity="left|top"
            android:inputType="textMultiLine" />
            
            <Button
                android:id="@+id/btnedit_address"
                android:layout_width="110dp"
                android:layout_height="45dp"
                android:background="@drawable/login_selector"
                android:gravity="center"
                android:text="Edit"
                android:layout_marginTop="50dp"
                android:layout_below="@+id/cusadd"
                android:layout_alignRight="@+id/cusphone"
                android:layout_alignEnd="@+id/cusphone" />

            Button
            android:id="@+id/btndel_address"
            android:layout_width="110dp"
            android:layout_height="wrap_content"
            android:layout_alignEnd="@+id/cusadd"
            android:layout_alignRight="@+id/cusadd"
            android:layout_alignTop="@+id/btnedit_address"
            android:layout_x="153dp"
            android:layout_y="383dp"
            android:background="@drawable/login_selector"
            android:text="Delete" />

            Button
            android:id="@+id/btn_updaddress"
            android:layout_width="110dp"
            android:layout_height="wrap_content"
            android:layout_x="153dp"
            android:layout_y="383dp"
            android:background="@drawable/login_selector"
            android:text="New Address"
            android:layout_alignTop="@+id/btn_backhome"
            android:layout_toRightOf="@+id/cusphone"
            android:layout_toEndOf="@+id/cusphone" />

            <Button
                android:id="@+id/btn_backhome"
                android:layout_width="110dp"
                android:layout_height="45dp"
                android:background="@drawable/login_selector"
                android:text="Back"
                android:layout_below="@+id/cusadd"
                android:layout_marginTop="50dp"
                android:layout_alignParentRight="true"
                android:layout_alignParentEnd="true" />


        </RelativeLayout>
    </RelativeLayout>
</ScrollView>
    </FrameLayout>

    <!-- Listview to display slider menu -->
    <ListView
        android:id="@+id/lists_slidermenu"
        android:layout_width="240dp"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:choiceMode="singleChoice"
        android:divider="@color/list_divider"
        android:dividerHeight="1dp"
        android:listSelector="@drawable/list_selector"
        android:background="@color/list_background"/>
</android.support.v4.widget.DrawerLayout>
