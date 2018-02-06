package projecto;
import javax.swing.JOptionPane;
import java.util.Formatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Projecto
{

    public static void main(String[] args) throws FileNotFoundException
    {
        final int TAMANHO =50;
        String[] turma = new String[TAMANHO];
        String[] numero = new String[TAMANHO];
        String[] nomes = new String[TAMANHO];
        int[] alg = new int[TAMANHO];
        int[] java = new int[TAMANHO];
        int[] vb = new int[TAMANHO];
        int nElems=0, op;
        do
        {
          op= menu();
          switch (op)
          {
             case 0:
                  JOptionPane.showMessageDialog(null, "Fim...");
                  break;
             case 1:
                  if (nElems < TAMANHO)
                  {
                    inserir(turma, numero, nomes, alg, java, vb, nElems);
                    nElems++;
                  } 
                  else
                  {
                      JOptionPane.showMessageDialog(null, "Atingida a capacidade maxima do vetor");
                  }
                  break;
              case 2:
                  if (nElems > 0)
                  {
                    listar(turma, numero, nomes, alg, java, vb, nElems);
                  } 
                  else
                  {
                      JOptionPane.showMessageDialog(null, "Nao Existem alunos");
                  }
                  break; 
              case 3:
                  if (nElems > 0)
                  {
                    atualizar(nomes, alg, java, vb, nElems);
                  } 
                  else
                  {
                      JOptionPane.showMessageDialog(null, "Nao Existem alunos");
                  }
                  break;
              case 4:
                  if (nElems>0)
                  {
                      if(eliminar(turma, numero, nomes, alg, java, vb, nElems)== true)
                      {
                      JOptionPane.showMessageDialog(null, "Aluno Eliminado");
                      nElems--;
                      }
                      else
                      {
                      JOptionPane.showMessageDialog(null,"Nao há qualquer aluno com esse nome");
                      }
                  }
                  else
                  {
                      JOptionPane.showMessageDialog(null, "Nao existem alunos");
                  }   
                  break;
              case 5:
                  if (nElems > 0)
                  {
                      ordenar(nomes, alg, java, vb, nElems);
                      listar(turma, numero, nomes, alg, java, vb, nElems);
                  }
                  else
                  {
                      JOptionPane.showMessageDialog(null, "Nao exitem alunos");
                  }
                  break;
              case 6:
                  if(nElems > 0)
                  {
                      gravar(turma, numero, nomes, alg, java, vb, nElems);
                  }
                  else
                  {
                      JOptionPane.showMessageDialog(null, "Nao existem alunos");
                  }
          }
        } 
        while (op !=0);
    }

    private static int menu() 
    {
      int op;
      op = Integer.parseInt(JOptionPane.showInputDialog(
        "1- Adicionar aluno\n"
      + "2- Listar alunos\n" 
      + "3- Atualizar nota de um aluno\n" 
      + "4- Eliminar aluno\n"
      + "5- Mostrar Informaçao ordenada por nome do aluno\n" 
      + "6- Carregar dados para a memoria\n"
      + "7-Gravar Dados em Ficheiro\n" 
      + "0 - Sair\n"));
      return op;
    }

    private static void inserir(String[] turma, String[] numero, String[] nomes, int[] alg, int[] java,int[] vb, int nElems) 
    {
        turma[nElems] = JOptionPane.showInputDialog("Turma do Aluno?");
        numero[nElems] = JOptionPane.showInputDialog("Numero do Aluno?");
        nomes[nElems] = JOptionPane.showInputDialog("Nome do Aluno?");
        alg[nElems] = Integer.parseInt(JOptionPane.showInputDialog("Insira a nota de Algoritmia"));
        java[nElems] = Integer.parseInt(JOptionPane.showInputDialog("Insira a nota Java"));
        vb[nElems] = Integer.parseInt(JOptionPane.showInputDialog("Insira a nota de Visual Basic"));
    }

    private static void listar(String[] turma, String[] numero, String[] nomes, int[] alg, int[] java,int[] vb, int nElems) 
    {
        int x;
        String msg= "Listagem de alunos";
        for (x=0; x<nElems;x++)
        { 
        msg+= "\n" + turma[x] + "-" + numero[x] + "-" + nomes[x] + "- Algoritmia -" + alg[x] + ", Java - " + java[x] + ", Visual Basic -" + vb[x];
        }
        JOptionPane.showMessageDialog(null, msg);
    }

    private static void atualizar(String[] nomes,  int[] alg, int[] java,int[] vb, int nElems) 
    {
       String nome;
       int pos;
       nome = JOptionPane.showInputDialog("qual o aluno cujo nota pretende alterar");
       pos = pesquisar(nomes,nElems,nome);
       if (pos != -1)
       {
            alg[pos] = Integer.parseInt(JOptionPane.showInputDialog("Qual a nova nota a Algoritmia?"));
            java[pos] = Integer.parseInt(JOptionPane.showInputDialog("Qual a nova nota a Java"));
            vb[pos] = Integer.parseInt(JOptionPane.showInputDialog("Qual a nova nota a Visual Basic?"));
       }
       else
       {
            JOptionPane.showMessageDialog(null,"Nao há qualquer aluno com o nome introduzido");
           
       }
    }

    private static int pesquisar(String[] nomes, int nElems, String nome) 
    {
        int pos=0;
        while(pos<nElems && nome.equalsIgnoreCase(nomes[pos])== false)
        {
           pos++; 
        }
        if (pos< nElems)
        {
            return pos;
        }
        else
        {
            return -1;
        }
    }

    private static boolean eliminar(String[] turma, String[] numero, String[] nomes, int[] alg, int[] java,int[] vb, int nElems) 
    {
        String nome;
        int pos,x;
        nome =JOptionPane.showInputDialog("Qual é o aluno que pretende Eliminar?");
        pos = pesquisar(nomes,nElems, nome);
        if(pos != -1)
        {
            for (x=pos;x<nElems;x++)
            {
                turma[x]=turma[x+1];
                numero[x]=numero[x+1];
                nomes[x]=nomes[x+1];
                alg[x] = alg[x+1];
                java[x] = java[x+1];
                vb[x] = vb[x+1];
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    private static void ordenar(String[] nomes,  int[] alg, int[] java,int[] vb, int nElems) 
    {
        int x , y , aux1;
        String aux2;
        for(x=0; x< nElems; x++)
        {
            for(y=0; y<nElems; y++)
            {
                if(nomes[y].compareToIgnoreCase(nomes[x])<0)
                {
                    aux2=nomes[x];
                    nomes[x]=nomes[y];
                    nomes[y]=aux2;
                    aux1= alg[x];
                    alg[x]=alg[y];
                    alg[y]=aux1;
                            
                }
            }
        }
    }

    private static void gravar(String[] turma, String[] numero, String[] nomes, int[] alg, int[] java,int[] vb, int nElems) throws FileNotFoundException 
    {
        Formatter files3 = new Formatter(new File("alunos.txt"));
        int x;
        for (x=0; x < nElems; x++)
        {
            if(x==0)
            {
                files3.format(turma[x] + "-" + numero[x] + "-" + nomes[x]+ ":" + alg[x] + "," + java[x] + "," + vb[x]);
            }
            else
            {
                files3.format("\n" + turma[x] + "-" + numero[x] + "-" + nomes[x]+ ":" + alg[x] + "," + java[x] + "," + vb[x]);
            }
            files3.close();
        }
        
    }
    private static int ler(String[] nomes, int[] alg, int[] java,int[] vb, int nElems) throws FileNotFoundException {
        Scanner fichFunc = new Scanner(new File("alunos.txt"));
        String linha;
        int pos, cont = 0;
        
        while(fichFunc.hasNextLine()==true && nElems < nomes.length) {
            linha = fichFunc.nextLine();
            String[] vetLinha = linha.split(":");
            pos = pesquisar(nomes, nElems, vetLinha[0]);
            if (pos == -1) {
                nomes[nElems] = vetLinha[0];
                alg[nElems] = Integer.parseInt(vetLinha[1]);
                nElems++;
                cont++;
            }
        }
        fichFunc.close();
        JOptionPane.showMessageDialog(null, "Numero de alunos carregados: " + cont);
        return nElems;
    }
}