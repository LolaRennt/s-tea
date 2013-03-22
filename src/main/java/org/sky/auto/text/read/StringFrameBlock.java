package org.sky.auto.text.read;

public class StringFrameBlock extends Block{
	private String text;
	private ChainLink chain;
	public StringFrameBlock(String str) {
		this.setText(str);
		chain =new ChainLink();
		chain.setIsFrameElement(true);
	}
	/**取得元素的定位方式*/
	public ChainLink getLocation(){
		chain=getFrameLocation();
		if(getType()!=null){
			chain.setType(getType());
		}else{
			chain.setType("element");
		}
		String entity=getEntity();
		String[] text=entity.split(Block.SPLIT);
		for(String str:text){
			if(str.toLowerCase().contains("css")||str.toLowerCase().contains("id")||str.toLowerCase().contains("linktext")||
					str.toLowerCase().contains("name")||str.toLowerCase().contains("class")
					||str.toLowerCase().contains("partial")
					||str.toLowerCase().contains("tagname")){
				if(str.toLowerCase().contains(Block.ELEMENT_LINK)){
					if(str.contains(ELEMENT_LINK)){
						//获取元素定位的行内容,并且切分元素的链/
						String[] strs=str.split(Block.ELEMENT_LINK);
						//获取到单个定位元素的内容
						chain.save(strs);
					}else{
						chain.save(str);
					}
				}else{
					chain.save(str);
				}
			}
			
		}
		return chain;
	}
	/**得到这个块的名称，也就是xml里面的ID值*/
	public String getLocationName(){
		String name = getText().substring(0,getText().indexOf(BLOCK_START));
		return name;
	}
	
	
	/**返回定义的type类型*/
	public String getType(){
		String entity = getEntity();
		String[] text=entity.split(Block.SPLIT);
		for(String str: text){
			if(str.toLowerCase().contains(Block.TYPE)){
				String[] strs=str.split(":");
				return strs[1];
			}
		}
		return null;
	}

	/**得到内容的实体*/
	private String getEntity(){
		String entity = getText().substring(getText().indexOf(BLOCK_START)+1,getText().lastIndexOf(BLOCK_END));
		return entity;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	} 
	
	private String getFrameEntity(){
		String text=getText();
		return getText().substring(text.indexOf("Frame"+Block.BLOCK_START),text.lastIndexOf(Block.BLOCK_END));
	}
	
	public ChainLink getFrameLocation(){
		String framelocation = getFrameEntity().split(";")[0];
		if(framelocation.contains(Block.ELEMENT_LINK)){
			String[] strs=framelocation.split(Block.ELEMENT_LINK);
			for(String str:strs){
				chain.saveFrame(str);
			}
		}
		return chain;
	}
	

	public void setChain(ChainLink chain) {
		this.chain = chain;
	}
	
}
