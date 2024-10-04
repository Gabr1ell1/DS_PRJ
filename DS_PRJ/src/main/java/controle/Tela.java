package controle;

import java.awt.*;
import java.text.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

import javax.swing.table.DefaultTableModel;//para o reconhecimento da JTable
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import conexao.conexao;

import java.sql.*;



public class Tela extends JFrame{//FrmTelaCad
    
    conexao con_cliente;
    
    JLabel img, rCodigo, rNome, rEmail, rTel, rData, pesq;
    JTextField  txtCod, txtNome, txtEmail, txtpesq;
    JFormattedTextField tel, data;
    MaskFormatter mTel, mData;
    JButton primeiro, anterior, proximo, ultimo;
    ImageIcon pesquisar, sair;//íCONES DOS BOTÕES
    
    JTable tblClientes;//datagrid
    JScrollPane scp_tabela;//container para o datagrid
    
   public Tela(){//FrmTelaCad
       
       con_cliente = new conexao();
       con_cliente.conecta();
            
       setTitle("Conexão Java com MySql");
       setResizable(false);
       Container tela = getContentPane();
       setLayout(null);
        
      //--------------- ESTILIZAÇÃO -------------------- 
      //ICONE DA JANELA
      ImageIcon icone = new ImageIcon("bancodedados.png");
      setIconImage(icone.getImage());
      
      //ADICIONANDO UMA IMAGEM A PÁGINA
      ImageIcon icon = new ImageIcon("dados.png");
      img = new JLabel(icon);
      img.setBounds(700,0,200,200);
      tela.add(img);
      
      tela.setBackground(Color.white);
     //----------------------------------- 
      
     
      //CÓDIGO
      rCodigo = new JLabel("Código: ");
      txtCod = new JTextField(10);
      rCodigo.setBounds(50,20,80,20);
      txtCod.setBounds(130,20,200,20);
      //ALTERANDO FONTE
      rCodigo.setFont(new Font("Arial",Font.BOLD,13));
   
      tela.add(rCodigo);
      tela.add(txtCod);
     
      //NOME
      rNome = new JLabel("Nome: ");
      txtNome = new JTextField(50);
      //POSICIONANDO
      rNome.setBounds(50,50,80,20);
      txtNome.setBounds(130,50,200,20);
      //ALTERANDO FONTE
      rNome.setFont(new Font("Arial",Font.BOLD,13));
      
      tela.add(rNome);
      tela.add(txtNome);
      
      try {
        mTel = new MaskFormatter("(##) ####-####");
        mData = new MaskFormatter("##/##/####");
        data = new JFormattedTextField(mData);
        tel = new JFormattedTextField(mTel);
    } catch (ParseException e) {
        e.printStackTrace();
    }


      //DATA
      rData = new JLabel("Data: ");
     
     //POSICIONANDO  
      rData.setBounds(50,80,80,20);
      data.setBounds(130,80,200,20);
      //ALTERANDO FONTE
      rData.setFont(new Font("Arial",Font.BOLD,13));
      
      tela.add(rData);
      tela.add(data);
      
      
      //TELEFONE
      rTel = new JLabel("Telefone: ");
      
      //POSICIONANDO
      rTel.setBounds(50,110,80,20);
      tel.setBounds(130,110,200,20);
      //ALTERANDO FONTE
      rTel.setFont(new Font("Arial",Font.BOLD,13));
      
      tela.add(rTel);
      tela.add(tel);
      
      
      //EMAIL
      rEmail = new JLabel("Email: ");
      txtEmail = new JTextField();
      //POSICIONANDO
      rEmail.setBounds(50,140,80,20);
      txtEmail.setBounds(130, 140, 200, 20);
      //ALTERANDO FONTE
      rEmail.setFont(new Font("Arial",Font.BOLD,13));
      
      tela.add(rEmail);
      tela.add(txtEmail);
          
//TABELA
//configuração da Jtable

       tblClientes = new javax.swing.JTable();
       scp_tabela = new javax.swing.JScrollPane();
       
       tblClientes.setBounds(50,270,710,200);
       scp_tabela.setBounds(50,270,710,200);
       
       tblClientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
       tblClientes.setFont(new java.awt.Font("Arial", 1, 12));
   
       tela.add(tblClientes); tela.add(scp_tabela);
       
       tblClientes.setModel(new javax.swing.table.DefaultTableModel(
               new Object [][]{
                   {null, null, null, null, null},
                   {null, null, null, null, null},
                   {null, null, null, null, null},
                   {null, null, null, null, null}
               },
               new String [] {"Código", "Nome", "Data Nascimento", "Telefone", "Email"})
           {
           boolean[] canEdit = new boolean[]{
               false, false, false, false, false};
           
           public boolean isCellEditable(int rowIndex, int columnIndex){
               return canEdit [columnIndex];}
           });
               scp_tabela.setViewportView(tblClientes);
             
               tblClientes.setAutoCreateRowSorter(true);//ativa a classificação ordenada da tabela
            
//FIM DAS CONFIGURAÇÕES DA JTabel

               
               //BOTÕES 
               primeiro = new JButton("Primeiro");
               anterior = new JButton("Anterior");
               proximo = new JButton("Próximo");
               ultimo = new JButton("Último");
               primeiro.setBounds(50,180,100,30);
               anterior.setBounds(150,180,100,30);
               proximo.setBounds(250,180,100,30);
               ultimo.setBounds(350,180,100,30);
              
               tela.add(primeiro);
               tela.add(anterior);
               tela.add(proximo);
               tela.add(ultimo);
               
               
               
               
               
                 //AÇÃO BOTÕES 
        primeiro.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try{
                        con_cliente.resultset.first();
                        mostrar_Dados();
                    }catch(SQLException erro){
                            JOptionPane.showMessageDialog(null, "Não foi possível acessar o primeiro registro");
                    }
                }
            }
        );
        anterior.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try{
                        con_cliente.resultset.previous();
                        mostrar_Dados();
                    }catch(SQLException erro){
                            JOptionPane.showMessageDialog(null, "Não foi possível acessar registro anterior");
                    }
                }
            }
        );    
        proximo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    con_cliente.resultset.next();
                    mostrar_Dados();
                }catch(SQLException erro){
                        JOptionPane.showMessageDialog(null, "Não foi possível acessar o próximo registro");
                }
            }
        }
    );
    ultimo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    con_cliente.resultset.last();
                    mostrar_Dados();
                }catch(SQLException erro){
                        JOptionPane.showMessageDialog(null, "Não foi possível acessar o último registro");
                }
            }
        }
    );   
    
    
    //BOTÕES OPERAÇÕES
               //ICONE DO BOTÃO NOVO REGISTRO
               ImageIcon NewR = new ImageIcon("novo_registro.png");
               JButton novo = new JButton("Novo registro", NewR);
               JButton gravar = new JButton("Gravar");
               JButton alterar = new JButton("Alterar");
               JButton excluir = new JButton("Excluir");
               
               
               novo.setBounds(510,180,140,30);
               gravar.setBounds(650,180,100,30);
               alterar.setBounds(750,180,100,30);
               excluir.setBounds(850,180,100,30);
               
               
              
               tela.add(novo);
               tela.add(gravar);
               tela.add(alterar);
               tela.add(excluir);
             
    
//LIMPANDO
        novo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                txtCod.setText("");//limpa as caixas de textos
                txtNome.setText("");
                data.setText("");
                tel.setText("");
                txtEmail.setText("");
                txtCod.requestFocus();
            }
        }
    );
        //GRAVANDO UM NOVO REGISTRO
    gravar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               String nome = txtNome.getText();
               String data_nasc = data.getText();
               String telefone = tel.getText();
               String email = txtEmail.getText();
            
            try{
                
String insert_sql="insert into tbclientes (nome,telefone, email, dt_nasc) values ('" + nome + "','" + telefone + "','" + email + "','" + data_nasc + "')";                
                
                
                con_cliente.statement.executeUpdate(insert_sql);
                JOptionPane.showMessageDialog(null, "Gravação realizada com sucesso!!", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
           
                con_cliente.executaSQL("select * from tbclientes order by cod");
                preencherTabela();
            
            }catch(SQLException errosql){
                JOptionPane.showMessageDialog(null,"\n Erro na gravação :\n " +errosql, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    );   
    //ALTERANDO UM REGISTRO
        alterar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               String nome= txtNome.getText();
               String data_nasc= data.getText();
               String telefone= tel.getText();
               String email = txtEmail.getText();
               String sql;
               String msg="";
               
               
              try{
               if(txtCod.getText().equals("")){
                   sql="insert into tbclientes (nome,telefone,email,dt_nasc) values ('" + nome + "','" + telefone + "','" + email + "','" + data_nasc + "')";
                   msg="Gravação de um novo registro";
               }else{
                   sql="Update tbclientes set nome='" + nome + "', telefone='" + telefone + "', email='" + email + "', dt_nasc='" +data_nasc + "'where cod="+txtCod.getText();
                   msg= "Alteração de registro";
               }
               
               con_cliente.statement.executeUpdate(sql);
               JOptionPane.showMessageDialog(null, "Gravação realizada com sucesso!!","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
               
               con_cliente.executaSQL("select * from tbclientes order by cod");
               preencherTabela();
               
            }catch(SQLException errosql){
                 JOptionPane.showMessageDialog(null,"\n Erro na Gravação :\n"+errosql,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    );
        //EXCLUINDO UM REGISTRO
    excluir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                 String sql="";
           try{
               int resp = JOptionPane.showConfirmDialog(rootPane, "Deseja Excluir o registro: ","Confrimar Exclusão", JOptionPane.YES_NO_OPTION,3);
               if(resp==0){
                   sql="delete from tbclientes where cod = "+txtCod.getText();
                   int excluir = con_cliente.statement.executeUpdate(sql);
                   if(excluir == 1){
                       JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!!","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                       con_cliente.executaSQL("select * from tbclientes order by cod");
                       con_cliente.resultset.first();
                       preencherTabela();
                       posicionarRegistro();
                   }
               }else{
                   JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário!!","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
               }
           }catch(SQLException excecao){
               JOptionPane.showMessageDialog(null,"Erro na Exclusão: "+excecao,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    );   
    
    //PESQUISA
    JLabel pesq = new JLabel("Pesquisar pelo nome do cliente: ");
    JTextField txtpesq = new JTextField("");

    //ICONE DO BOTÃO PESQUISAR 
    pesquisar = new ImageIcon("pesquisar.png");
    JButton bpesq = new JButton("Pesquisar", pesquisar);
    
    
    pesq.setBounds(50,504,280,25);
    txtpesq.setBounds(240,504,100,25);
    
    
     bpesq.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
           try{
               String pesquisa = "select * from tbclientes where nome like '" +txtpesq.getText() +"%'";
               con_cliente.executaSQL(pesquisa);
               
               if(con_cliente.resultset.first()){
                   preencherTabela();
               }
               else{
                   JOptionPane.showMessageDialog(null,"\n Não existe dados com este pramêtro!!","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
               }
           }catch(SQLException errosql){
               JOptionPane.showMessageDialog(null,"\n Os dados digitados não  foram localizados!! :\n"+errosql,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
           }
        }});
    bpesq.setBounds(400,504,130,25);
     
    tela.add(bpesq);
    tela.add(pesq);
    tela.add(txtpesq);

    //ICONE DO BOTÃO SAIR 
    sair = new ImageIcon("sair.png");
    JButton bsair = new JButton("Sair", sair);
   
    
    bsair.setBounds(850,504,100,25);
    bsair.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        }
    );   
    
    tela.add(bsair);
    
               setSize(1000,600);
               setVisible(true);
               setLocationRelativeTo(null);
               
              
               con_cliente.executaSQL("Select * from tbclientes order by cod");
               preencherTabela();
               posicionarRegistro();

            }
       
   public void preencherTabela()
   {
       //AJUSTA A LARGURA DAS COLUNAS
   tblClientes.getColumnModel().getColumn(0).setPreferredWidth(4);
   tblClientes.getColumnModel().getColumn(1).setPreferredWidth(150);
   tblClientes.getColumnModel().getColumn(2).setPreferredWidth(11);
   tblClientes.getColumnModel().getColumn(3).setPreferredWidth(11);
   tblClientes.getColumnModel().getColumn(4).setPreferredWidth(100);
   
   DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
   modelo.setNumRows(0);
   
   try{
        con_cliente.resultset.beforeFirst();
        while(con_cliente.resultset.next()){
        modelo.addRow(new Object[]{
            con_cliente.resultset.getString("cod"),
            con_cliente.resultset.getString("nome"), 
            con_cliente.resultset.getString("dt_nasc"), 
            con_cliente.resultset.getString("telefone"), 
            con_cliente.resultset.getString("email")
        });
    }
   }catch(SQLException erro){
    JOptionPane.showMessageDialog(null, "\n Erro ao listar dados da tabela!! :\n "
            + ""+erro, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
   }
   
  }
   //Servirá para posicionar no 1º registro da tabela, ou seja, na linha do cliente “Marco Lopes”
  public void posicionarRegistro(){
        try{
            con_cliente.resultset.first();//posiciona no 1° registro da tabela
            mostrar_Dados();//chama o método que irá buscar o dado da tabela 
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null,"Não foi possível posicionar no primeiro registro: " +erro,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
        }
        
  }
   

 //o método mostrar_Dados() irá carregar com dados da tabela as caixas de texto:
  public void mostrar_Dados(){
    try{
        txtCod.setText(con_cliente.resultset.getString("cod"));//Associar a caixa de texto ao campo cod
        txtNome.setText(con_cliente.resultset.getString("nome"));//Associar a caixa de texto ao campo nome
        data.setText(con_cliente.resultset.getString("dt_nasc"));//Associar a caixa de texto ao campo dt_data
        tel.setText(con_cliente.resultset.getString("telefone"));//Associar a caixa de texto ao campo telefone
        txtEmail.setText(con_cliente.resultset.getString("email"));//Associar a caixa de texto ao campo telefone

  }catch (SQLException erro){
    JOptionPane.showMessageDialog(null,"Não localizou dado:"+erro,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
    }
  }
  
   public static void main(String[] args) {
       Tela app = new Tela();
       app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}





