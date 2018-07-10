package br.com.tiberio.fechamentoexterno.atividades;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.com.tiberio.fechamentoexterno.R;
import br.com.tiberio.fechamentoexterno.task.TaskLogin;
import br.com.tiberio.fechamentoexterno.webservice.WebServiceSoap;

public class Login extends AppCompatActivity {

    private WebServiceSoap wssoap;
    private Button btlogin;
    private EditText txtlogin;
    private EditText txtsenha;
    public  String res2 = "";
    SharedPreferences mPrefs = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        //Método para Colocar Icone na action bar ao lado do Titulo
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle( "" );
        //actionbar.setDisplayHomeAsUpEnabled(true);
        //actionbar.setHomeAsUpIndicator(R.drawable.appicontoolbar);

        //Button btlogar;
        btlogin = findViewById(R.id.id_botao_login);
        txtlogin = findViewById(R.id.txtlogin);
        txtsenha = findViewById(R.id.txtsenha);

        final CheckBox rememberMeCbx = (CheckBox)findViewById(R.id.saveLoginCheckBox);

        //Usar Preferencias de Usuários
        Context context = getApplicationContext();
        mPrefs  = context.getSharedPreferences("userdetails", MODE_PRIVATE);


        //recuperar valor
        Boolean rememberMe = mPrefs.getBoolean("rememberMe",false);
        String  rememberlogin = mPrefs.getString("login",null);
        String  rememberpassword = mPrefs.getString("password",null);


        //Recuperar Valores de Login em modo privado
        if (rememberMe==true){

            txtlogin.setText(rememberlogin);
            txtsenha.setText(rememberpassword);
            rememberMeCbx.setChecked( true );

        }




        //Monta o evento de click do botão logar
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //'Verifica se usuário marcou a opção de Lembrar usuário e senha e salva preferências
                    if(rememberMeCbx.isChecked()){
                    saveLoginDetails(mPrefs);
                }else {
                    // attemptLogin();
                   removeLoginDetails(mPrefs);
                }


                wssoap = new WebServiceSoap();
                if(!wssoap.VericarConexaoRede(getApplicationContext())){
                    Toast.makeText(getApplicationContext(), "Verifique seu plano de dados ou wifi.", Toast.LENGTH_LONG).show();
                }else{
                    //Efetuar Login via WS Soap no Notes
                    loginWS();
                };

            }
        });


    }

    public void abrirTela(String funcao, String user){
        try {
            Login.this.finish();

            Intent intent = new Intent(Login.this, Menuprincipal.class);
            intent.putExtra("pass", funcao);
            intent.putExtra("user", user);

//        intent.putExtra("parm2", "Teste parm2 ...");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

            //Teste Ok
        }catch(Exception ex){
            Log.e("Login", "Erro abrirTela", ex);
        }
    }




    //Método para Criar Login do Usuário
    private void loginWS() {



        if(!wssoap.VericarConexaoRede(getApplicationContext())){
            Toast.makeText(this, "Verifique seu plano de dados ou wifi.", Toast.LENGTH_LONG).show();

        }else{

            WebServiceSoap wssoap = new WebServiceSoap();
            if(!wssoap.VericarConexaoRede(getApplicationContext())) {
                Toast.makeText(getApplicationContext(), "Verifique seu plano de dados ou wifi.", Toast.LENGTH_LONG).show();
            }else{
                String res;

                try {
                    // Message stringMessage = Message.obtain(myTextHandler);
                    //res = ws.retonadados(usuario, parm);

                    //Chama o serviço Domino para Realizar Login
                    String chavebusca = txtlogin.getText().toString()+"#"+txtsenha.getText().toString();
                    res = new TaskLogin().execute( chavebusca, "" ).get();


                    if (txtlogin.getText().toString().isEmpty() || res.toString().equals("Usuário não existe")){
                        res2 = "Informe um Usuário válido.";
                        Toast.makeText(getApplicationContext(),res2.toString(), Toast.LENGTH_LONG).show();
                    }else if( txtsenha.getText().toString().isEmpty() || res.toString().equals("Senha não é válida")){
                        res2 = "Informe uma senha válida.";
                        Toast.makeText(getApplicationContext(),res2.toString(), Toast.LENGTH_LONG).show();
                    }else if( res.equals("Senha válida")){
                        res2 = "Login efetuado com sucesso!";
                        Toast.makeText(getApplicationContext(),res2.toString(), Toast.LENGTH_LONG).show();
                        abrirTela(txtsenha.getText().toString(),txtlogin.getText().toString());
                    }else{

                    }

                    //Toast.makeText(getApplicationContext(),"", Toast.LENGTH_LONG).show();


                }catch(Exception ex){
                    Log.e("MainActivity", "Erro na chamada", ex);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                } finally{

                }



            }



        }



    }

    private void saveLoginDetails(SharedPreferences mPrefs)
    {
        //fill input boxes with stored login and pass
        EditText loginEbx = (EditText)findViewById(R.id.txtlogin);
        EditText passEbx = (EditText)findViewById(R.id.txtsenha);
        String login = loginEbx.getText().toString();
        String upass = passEbx.getText().toString();


        SharedPreferences.Editor e = mPrefs.edit();
        e.putBoolean("rememberMe", true);
        e.putString("login", login);
        e.putString("password", upass);
        e.commit();
    }

    private void removeLoginDetails(SharedPreferences mPrefs)
    {
        SharedPreferences.Editor e = mPrefs.edit();
        e.putBoolean("rememberMe", false);
        e.remove("login");
        e.remove("password");
        e.commit();
    }




    ///Método para Montar o Menu Toobar da Aplicação
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuitens, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // display a message when a button was pressed
        String message = "";
        if (item.getItemId() == R.id.menu_action_sair) {
            //message = "You selected option 2!";

            //sair
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();

            }

        }
        else {
            //message = "Why would you select that!?";
        }

        // show message via toast
        //Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        //toast.show();

        return true;
    }


}
