package uit.tkorg.dbsa.gui.fetcher;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import uit.tkorg.dbsa.actions.database.LoadSubject;
import uit.tkorg.dbsa.gui.main.DBSAApplication;
import uit.tkorg.dbsa.model.Subject;

public class PopUpSubjectTree extends JWindow{

	private static final long serialVersionUID = 1L;
	public static JTree subjectJTree = null;
	public static JScrollPane subjectJScrollPane = null;

	public PopUpSubjectTree(){
		
		setSize(250, 400);
		getContentPane().setBackground(java.awt.Color.orange);
		setVisible(false);

		subjectJScrollPane = new JScrollPane(createSubjectTree());
		
		getContentPane().add(subjectJScrollPane);
	}
	
	public static JTree createSubjectTree(){
		if (subjectJTree == null) {
			subjectJTree = new JTree();		 
		
			ArrayList<Subject> subjectList = LoadSubject.getSubject();
			
			DefaultTreeModel treeModel = null;
			DefaultMutableTreeNode nodeMain = new DefaultMutableTreeNode("Danh sach cac chu de");
			DefaultMutableTreeNode nodeParrent = null;
			if(subjectList != null)
			for(int i = 0; i < subjectList.size(); i++){				
				
				if(subjectList.get(i).getId() >= 1 && subjectList.get(i).getId() < 10){
					
					DefaultMutableTreeNode nodeChildren;
					if(subjectList.get(i).getId() == 1){
						nodeParrent = new DefaultMutableTreeNode(subjectList.get(i).getSbj_name());
						
					}else if(nodeParrent != null){
						
						nodeChildren = new DefaultMutableTreeNode(subjectList.get(i).getSbj_name());
						nodeParrent.add(nodeChildren);
					}					
					
					if(nodeParrent != null)
						nodeMain.add(nodeParrent);
					
				}else if(subjectList.get(i).getId() >= 10 && subjectList.get(i).getId() < 14){

					DefaultMutableTreeNode nodeChildren;
					if(subjectList.get(i).getId() == 10){
						nodeParrent = new DefaultMutableTreeNode(subjectList.get(i).getSbj_name());
						
					}else if(nodeParrent != null){
						nodeChildren = new DefaultMutableTreeNode(subjectList.get(i).getSbj_name());
						nodeParrent.add(nodeChildren);
					}
					
					if(nodeParrent != null)
						nodeMain.add(nodeParrent);
					
				}else if(subjectList.get(i).getId() >= 14 && subjectList.get(i).getId() < 18){

					DefaultMutableTreeNode nodeChildren;
					if(subjectList.get(i).getId() == 14){
						nodeParrent = new DefaultMutableTreeNode(subjectList.get(i).getSbj_name());
						
					}else if(nodeParrent != null){
						nodeChildren = new DefaultMutableTreeNode(subjectList.get(i).getSbj_name());
						nodeParrent.add(nodeChildren);
					}
					
					if(nodeParrent != null)
						nodeMain.add(nodeParrent);
					
				}else if(subjectList.get(i).getId() >= 18 && subjectList.get(i).getId() < 23){

					DefaultMutableTreeNode nodeChildren;
					if(subjectList.get(i).getId() == 18){
						nodeParrent = new DefaultMutableTreeNode(subjectList.get(i).getSbj_name());
						
					}else if(nodeParrent != null){
						nodeChildren = new DefaultMutableTreeNode(subjectList.get(i).getSbj_name());
						nodeParrent.add(nodeChildren);
					}
					
					if(nodeParrent != null)
						nodeMain.add(nodeParrent);
					
				}else if(subjectList.get(i).getId() >= 23 && subjectList.get(i).getId() < 34){

					DefaultMutableTreeNode nodeChildren;
					if(subjectList.get(i).getId() == 23){
						nodeParrent = new DefaultMutableTreeNode(subjectList.get(i).getSbj_name());
					
					}else if(nodeParrent != null){
						nodeChildren = new DefaultMutableTreeNode(subjectList.get(i).getSbj_name());
						nodeParrent.add(nodeChildren);
					}
					
					if(nodeParrent != null)
						nodeMain.add(nodeParrent);
					
				}else if(subjectList.get(i).getId() >= 34 && subjectList.get(i).getId() < 43){

					DefaultMutableTreeNode nodeChildren;
					if(subjectList.get(i).getId() == 34){
						nodeParrent = new DefaultMutableTreeNode(subjectList.get(i).getSbj_name());
						
					}else if(nodeParrent != null){
						nodeChildren = new DefaultMutableTreeNode(subjectList.get(i).getSbj_name());
						nodeParrent.add(nodeChildren);
					}
					
					if(nodeParrent != null)
						nodeMain.add(nodeParrent);
					
				}else{

					DefaultMutableTreeNode nodeChildren;
					if(subjectList.get(i).getId() == 43){
						nodeParrent = new DefaultMutableTreeNode(subjectList.get(i).getSbj_name());						
					}else if(nodeParrent != null){
						nodeChildren = new DefaultMutableTreeNode(subjectList.get(i).getSbj_name());
						nodeParrent.add(nodeChildren);
					}
					if(nodeParrent != null)
						nodeMain.add(nodeParrent);
				}
				treeModel = new DefaultTreeModel(nodeMain);
				subjectJTree.setModel(treeModel);
			}
		}
		
			
//			{
//
//				{
//					DefaultMutableTreeNode nodeParrent = new DefaultMutableTreeNode("Theoretical computer science");
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Mathematical logic");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Automata theory");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Number theory");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Graph theory");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Type theory");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Category theory ");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Computational geometry");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Quantum computing theory");
//						nodeParrent.add(nodeChildren);
//					}
//					
//					nodeMain.add(nodeParrent);
//				}
//				{
//					DefaultMutableTreeNode nodeParrent = new DefaultMutableTreeNode("Algorithms and data structures");
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Analysis of algorithms");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Algorithms");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Data structures");
//						nodeParrent.add(nodeChildren);
//					}
//					
//					nodeMain.add(nodeParrent);
//				}
//				{
//					DefaultMutableTreeNode nodeParrent = new DefaultMutableTreeNode("Computer elements and architecture");
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Digital logic");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Microarchitecture");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Multiprocessing");
//						nodeParrent.add(nodeChildren);
//					}
//					
//					nodeMain.add(nodeParrent);
//				}
//				{
//					DefaultMutableTreeNode nodeParrent = new DefaultMutableTreeNode("Computational science");
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Numerical analysis");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Computational physics");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Computational chemistry");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Bioinformatics");
//						nodeParrent.add(nodeChildren);
//					}
//					
//					nodeMain.add(nodeParrent);
//				}
//				{
//					DefaultMutableTreeNode nodeParrent = new DefaultMutableTreeNode("Artificial Intelligence");
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Machine learning");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Computer vision");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Natural language processing/Computational linguistics");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Robotics");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Image Processing");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Pattern Recognition");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Congnitive science");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Evolutionary computation");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Information retrieval");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Knowledge Representation");
//						nodeParrent.add(nodeChildren);
//					}
//					
//					
//					nodeMain.add(nodeParrent);
//				}
//				{
//					DefaultMutableTreeNode nodeParrent = new DefaultMutableTreeNode("Software Engineering");
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Operating systems");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Computer networks");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Databases");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Computer security");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Ubiquitous computing");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Systems architecture");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Compiler design");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Programming languages");
//						nodeParrent.add(nodeChildren);
//					}
//							
//					nodeMain.add(nodeParrent);
//				}
//				{
//					DefaultMutableTreeNode nodeParrent = new DefaultMutableTreeNode("Other");
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Datamining");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Information Retrieval");
//						nodeParrent.add(nodeChildren);
//					}
//					{
//						DefaultMutableTreeNode nodeChildren = new DefaultMutableTreeNode("Information Extraction");
//						nodeParrent.add(nodeChildren);
//					}
//					
//							
//					nodeMain.add(nodeParrent);
//				}			
//		}
		subjectJTree.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					String str = getItem();
					DBSAApplication.fetcherPanel.setTextJCombobox(str);
				}
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
			
			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}
			
		});
		
		return subjectJTree;
	}
	
	public static String getItem(){
		String result = "";
		if(subjectJTree != null){
			TreePath treePath = subjectJTree.getSelectionPath();
			
			String str = treePath.toString();
			
			int begin = str.lastIndexOf(",");
			int end = str.lastIndexOf("]");
			
			result = str.substring(begin + 2, end);
		}
		
		return result;
	}
	
}
