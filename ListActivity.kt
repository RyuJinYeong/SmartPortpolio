package kr.ac.hallym.portfolio

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kr.ac.hallym.portfolio.databinding.ActivityListBinding
import kr.ac.hallym.portfolio.databinding.FragmentInfoBinding

class ListActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        var infoBinding = FragmentInfoBinding.inflate(layoutInflater)
        var id = intent.getStringExtra("id")
        super.onCreate(savedInstanceState)
        val binding = ActivityListBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val viewPager = binding.viewpager
        val tabLayout = binding.tabs
        viewPager.adapter = MyFragmentPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = "인적사항"
                1 -> tab.text = "이수과목"
                2 -> tab.text = "PROJECT"
            }
        }.attach()


        binding.toolbar.setTitle("스마트 포트폴리오")
        setSupportActionBar(binding.toolbar)

        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        binding.drawerView.setNavigationItemSelectedListener {
            Log.d("nav","네비게이션 버튼 클릭 : ${it.title}")
            if(it.title.equals("깃허브")){
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("${infoBinding.gitadd.text}"))
                startActivity(intent)
            }
            if(it.title.equals("이메일")){
                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("plain/text");
                val address = "${infoBinding.email.text}"
                intent.putExtra(Intent.EXTRA_EMAIL, address)
                startActivity(intent)
            }
            if(it.title.equals("전화")){
                val pnum = infoBinding.phone.text
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+pnum))
                startActivity(intent)
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    val fragments: List<Fragment>
    init {
        fragments = listOf(InfoFragment(), SubFragment(), ProjectFragment())
    }
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}