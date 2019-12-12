import javax.script.*;

public class Test {


    public static void execute(ScriptEngineManager manager, String engineName,
                               String script) {

        ScriptEngine engine = manager.getEngineByName(engineName);
        if (engine == null) {
            System.out.println(engineName + " is not available.");
            return;
        }

        try {
            ScriptContext ctx = engine.getContext();
            //System.out.println(ctx);

            ctx.setAttribute("a", "abc", ScriptContext.ENGINE_SCOPE);
            SimpleBindings bs = new SimpleBindings(){
                public Object get(Object key) {
                    Object res = super.get(key);
                    if(res==null){
                        return "1234";
                    }
                    return res;
                }
                public boolean containsKey(Object key) {
                    return true;
                }
            };

            ctx.setBindings(bs,ScriptContext.ENGINE_SCOPE);
            //ctx.setAttribute("a", "abc", ScriptContext.ENGINE_SCOPE);

            engine.eval(script, ctx);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testScript(){
        // Get the script engine manager
        ScriptEngineManager manager = new ScriptEngineManager();

        execute(manager, "Groovy", "println(a)");
    }

}
