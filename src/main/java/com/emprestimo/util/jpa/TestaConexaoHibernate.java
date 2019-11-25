package com.emprestimo.util.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestaConexaoHibernate {
	public static void main(String[] args) {
		Connection conexao = null;
		try {
			String url = "jdbc:postgresql://localhost/emprestimo";
			String usuario = "postgres";
			String senha = "root";
			conexao = DriverManager.getConnection(url, usuario, senha);
			System.out.println("Conectou!!");
			conexao.close();
		} catch (SQLException e) {
			System.out.println("Ocorreu erro ao criar a conexão. Erro:" + e.getMessage());
		}
	}

}
