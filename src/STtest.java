public class STtest {

    public static void main(String[] args){

        SymbolTableOrdered<String,Integer> st = new SymbolTableOrdered<>();
        st.put("ahmad",300);
        st.put("mohammad",400);
        st.put("rawan",500);
        st.put("saad", 600);
        st.put("may", 450);
        st.put("Issa", 1000);
        st.put("Israa", 600);
        st.put("Heba", 100);
        st.put("Israa", 1600);

        System.out.println(st.get("rawan"));
        System.out.println(st.get("alaa"));
        System.out.println(st.contains("ahmad"));
        st.delete("ahmad");
        System.out.println(st.contains("ahmad"));
        System.out.println(st.size());

        for(String key: st.keys())
           System.out.println("key: " + key + " ,value: " + st.get(key));

    }
}