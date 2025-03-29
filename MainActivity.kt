package kr.ac.hallym.portfolio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kr.ac.hallym.portfolio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        var idpwcheck = false
        var check = true
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DBHelper(this).writableDatabase

        var name = "류진영"
        var id = "20185237"
        var pw = "test"
        var email = "wbc3@naver.com"
        var ph_num = "null"

        val cursor = db.rawQuery("select * from USER_TB", null)


        while(cursor.moveToNext()){
            var userid = cursor.getString(2).toString()
            var userpw = cursor.getString(3).toString()

            if(id.equals(userid)){
                check = false
                break;
            }
        }

        if(check) {
            db.execSQL(
                "insert into USER_TB (name, id, password, email, ph_num) values (?,?,?,?,?)",
                arrayOf<String>(name,id,pw,email,ph_num)
            )
        }


        binding.enterButton.setOnClickListener{
            var id = binding.UserID.text.toString()
            var pw = binding.UserPassword.text.toString()
            val db = DBHelper(this).readableDatabase
            val cursor = db.rawQuery("select * from USER_TB", null)

            while(cursor.moveToNext()){
                var userid = cursor.getString(2).toString()
                var userpw = cursor.getString(3).toString()

                Log.d("log", "입력아이디 : $id, id = $userid, pw = $userpw")

                if(id.equals(userid) && pw.equals(userpw)){
                    idpwcheck = true
                    break;
                }
                else {
                    idpwcheck = false
                }
            }
            if(idpwcheck){
                val intent = Intent(this, SplashActivity::class.java)
                intent.putExtra("id",id)
                startActivity(intent)
            }
            else {
                Toast.makeText(this,"아이디 또는 비밀번호를 잘못 입력했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}