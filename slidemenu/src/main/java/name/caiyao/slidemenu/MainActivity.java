package name.caiyao.slidemenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import name.caiyao.slidemenu.view.CustomSlidingMenu;

public class MainActivity extends AppCompatActivity {

    private CustomSlidingMenu mLeftMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLeftMenu = (CustomSlidingMenu) findViewById(R.id.sm);
    }

//    public void toggleMenu(View view) {
//        mLeftMenu.toggle();
//    }
}
