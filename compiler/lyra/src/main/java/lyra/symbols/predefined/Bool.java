package lyra.symbols.predefined;

import lyra.SemanticErrorException;
import lyra.scopes.Scope;
import lyra.symbols.ClassSymbol;
import lyra.symbols.VariableSymbol;

public class Bool extends AbstractPredefinedSymbol {

    @Override
    public void forward(Scope scope) {
        ClassSymbol c = new ClassSymbol("Bool", scope, (ClassSymbol)scope.resolve("Object"));
        try {
            forwardMethod(c, "toString", "String", false);
            forwardMethod(c, "__not",       "Bool", true);
            forwardMethod(c, "__equals",    "Bool", true, new ArgumentStrings("Bool", "rhs"));
            forwardMethod(c, "__notequals", "Bool", true, new ArgumentStrings("Bool", "rhs"));
            forwardMethod(c, "__and",       "Bool", true, new ArgumentStrings("Bool", "rhs"));
            forwardMethod(c, "__or",        "Bool", true, new ArgumentStrings("Bool", "rhs"));
            defineClass(scope, c);

            defineGlobal(scope, new VariableSymbol("false", c));
            defineGlobal(scope, new VariableSymbol("true",  c));
        } catch (SemanticErrorException e) {
            throw new RuntimeException("Compiler not obeying it's own rules.", e);
        }
    }
}