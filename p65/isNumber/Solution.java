//验证给定的字符串是否可以解释为十进制数字。
//
// 例如:
//
// "0" => true
//" 0.1 " => true
//"abc" => false
//"1 a" => false
//"2e10" => true
//" -90e3 " => true
//" 1e" => false
//"e3" => false
//" 6e-1" => true
//" 99e2.5 " => false
//"53.5e93" => true
//" --6 " => false
//"-+3" => false
//"95a54e53" => false
//
// 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：
//
//
// 数字 0-9
// 指数 - "e"
// 正/负号 - "+"/"-"
// 小数点 - "."
//
//
// 当然，在输入中，这些字符的上下文也很重要。
//
// 更新于 2015-02-10:
//C++函数的形式已经更新了。如果你仍然看见你的函数接收 const char * 类型的参数，请点击重载按钮重置你的代码。
// Related Topics 数学 字符串
// 👍 168 👎 0
package algorithm.p65.isNumber;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().isNumber("53.5e93"));
        System.out.println(new Solution().isNumber("-123.456e789"));
        System.out.println(new Solution().isNumber("-.9"));
        System.out.println(new Solution().isNumber("-9."));
        System.out.println(new Solution().isNumber("+9."));
        System.out.println(new Solution().isNumber(" 2e0 "));
        System.out.println(new Solution().isNumber(" 2E8 "));
        System.out.println("-----------------------------");
        System.out.println(new Solution().isNumber(" 2e1. "));
        System.out.println(new Solution().isNumber(" 2e.1 "));
        System.out.println(new Solution().isNumber("99e2.5"));
        System.out.println(new Solution().isNumber("-.9."));

        System.out.println("*************************************");

        System.out.println(new Solution().isNumberWithFiniteStateMachine("53.5e93"));
        System.out.println(new Solution().isNumberWithFiniteStateMachine("-123.456e789"));
        System.out.println(new Solution().isNumberWithFiniteStateMachine("-.9"));
        System.out.println(new Solution().isNumberWithFiniteStateMachine("-9."));
        System.out.println(new Solution().isNumberWithFiniteStateMachine("+9."));
        System.out.println(new Solution().isNumberWithFiniteStateMachine(" 2e0 "));
        System.out.println(new Solution().isNumberWithFiniteStateMachine(" 2E8 "));
        System.out.println("-----------------------------");
        System.out.println(new Solution().isNumberWithFiniteStateMachine(" 2e1. "));
        System.out.println(new Solution().isNumberWithFiniteStateMachine(" 2e.1 "));
        System.out.println(new Solution().isNumberWithFiniteStateMachine("99e2.5"));
        System.out.println(new Solution().isNumberWithFiniteStateMachine("-.9."));


        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

        System.out.println(new Solution().isNumberWithLaw("53.5e93"));
        System.out.println(new Solution().isNumberWithLaw("-123.456e789"));
        System.out.println(new Solution().isNumberWithLaw("-.9"));
        System.out.println(new Solution().isNumberWithLaw("-9."));
        System.out.println(new Solution().isNumberWithLaw("+9."));
        System.out.println(new Solution().isNumberWithLaw(" 2e0 "));
        System.out.println(new Solution().isNumberWithLaw(" 2E8 "));
        System.out.println(new Solution().isNumberWithLaw(" +2.8e+9 "));
        System.out.println("-----------------------------");
        System.out.println(new Solution().isNumberWithLaw("  "));
        System.out.println(new Solution().isNumberWithLaw(" 2e1. "));
        System.out.println(new Solution().isNumberWithLaw(" e9 "));
        System.out.println(new Solution().isNumberWithLaw(" 0e "));
        System.out.println(new Solution().isNumberWithLaw(" 2e.1 "));
        System.out.println(new Solution().isNumberWithLaw("99e2.5"));
        System.out.println(new Solution().isNumberWithLaw("-.9."));
        System.out.println(new Solution().isNumberWithLaw(".-4e"));
        System.out.println(new Solution().isNumberWithLaw(".4e5+"));

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");

        System.out.println(new Solution().isNumberWithLaw1("53.5e93"));
        System.out.println(new Solution().isNumberWithLaw1("-123.456e789"));
        System.out.println(new Solution().isNumberWithLaw1("-.9"));
        System.out.println(new Solution().isNumberWithLaw1("-9."));
        System.out.println(new Solution().isNumberWithLaw1("+9."));
        System.out.println(new Solution().isNumberWithLaw1(" 2e0 "));
        System.out.println(new Solution().isNumberWithLaw1(" 2E8 "));
        System.out.println(new Solution().isNumberWithLaw1(" +2.8e+9 "));
        System.out.println("-----------------------------");
        System.out.println(new Solution().isNumberWithLaw1("  "));
        System.out.println(new Solution().isNumberWithLaw1(" 2e1. "));
        System.out.println(new Solution().isNumberWithLaw1(" e9 "));
        System.out.println(new Solution().isNumberWithLaw1(" 0e "));
        System.out.println(new Solution().isNumberWithLaw1(" 2e.1 "));
        System.out.println(new Solution().isNumberWithLaw1("99e2.5"));
        System.out.println(new Solution().isNumberWithLaw1("-.9."));
        System.out.println(new Solution().isNumberWithLaw1(".-4e"));
        System.out.println(new Solution().isNumberWithLaw1(".-4"));
        System.out.println(new Solution().isNumberWithLaw1(".4e5+"));
    }

    enum State {
        STATE_INITIAL,  // 初始状态
        STATE_INT_SIGN, // 符号位
        STATE_INTEGER, // 整数部分(此状态结束为完整数字)
        STATE_POINT, // 左侧有整数小数点(此状态结束为完整数字)
        STATE_POINT_WITHOUT_INT, // 左侧无整数小数点
        STATE_FRACTION, // 小数部分(此状态结束为完整数字)
        STATE_EXP, // 字符e
        STATE_EXP_SIGN, // 指数符号
        STATE_EXP_INT, // 指数整数部分(此状态结束为完整数字)
        STATE_END // 结束状态(此状态结束为完整数字)
    }

    enum CharType {
        CHAR_SPACE, // 空格
        CHAR_NUMBER, // 数字
        CHAR_SIGN, // 符号
        CHAR_EXP, // 字符e
        CHAR_POINT, // 小数点
        CHAR_ILLEGAL // 非法字符
    }


    public static CharType toCharType(char s) {
        if (s == ' ')
            return CharType.CHAR_SPACE;
        else if (s >= '0' && s <= '9')
            return CharType.CHAR_NUMBER;
        else if (s == '-' || s == '+')
            return CharType.CHAR_SIGN;
        else if (s == 'e' || s == 'E')
            return CharType.CHAR_EXP;
        else if (s == '.')
            return CharType.CHAR_POINT;
        else
            return CharType.CHAR_ILLEGAL;
    }


    public static Map<State, Map<CharType, State>> transfer() {
        Map<State, Map<CharType, State>> transfer = new HashMap<>();
        Map<CharType, State> stateInitialMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_INITIAL);
            put(CharType.CHAR_SIGN, State.STATE_INT_SIGN);
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
        }};
        transfer.put(State.STATE_INITIAL, stateInitialMap);

        Map<CharType, State> stateIntSignMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
        }};
        transfer.put(State.STATE_INT_SIGN, stateIntSignMap);

        Map<CharType, State> stateIntegerMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_END);
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_POINT, State.STATE_POINT);
        }};
        transfer.put(State.STATE_INTEGER, stateIntegerMap);

        Map<CharType, State> statePointMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_END);
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
        }};
        transfer.put(State.STATE_POINT, statePointMap);

        Map<CharType, State> statePointWithoutIntMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
        }};
        transfer.put(State.STATE_POINT_WITHOUT_INT, statePointWithoutIntMap);

        Map<CharType, State> stateFractionMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_END);
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
        }};
        transfer.put(State.STATE_FRACTION, stateFractionMap);

        Map<CharType, State> stateExpMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
            put(CharType.CHAR_NUMBER, State.STATE_EXP_INT);
        }};
        transfer.put(State.STATE_EXP, stateExpMap);

        Map<CharType, State> stateExpSignMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
        }};
        transfer.put(State.STATE_EXP_SIGN, stateExpSignMap);

        Map<CharType, State> stateExpIntMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_END);
            put(CharType.CHAR_NUMBER, State.STATE_EXP_INT);
        }};
        transfer.put(State.STATE_EXP_INT, stateExpIntMap);

        Map<CharType, State> stateEndMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_END, stateEndMap);

        return transfer;
    }

    /**
     * 有限状态机版
     */
    public boolean isNumberWithFiniteStateMachine(String s) {
        Map<State, Map<CharType, State>> transfer = transfer();
        State state = State.STATE_INITIAL;
        for (int i = 0; i < s.length(); i++) {
            state = transfer.get(state).get(toCharType(s.charAt(i)));
            if (state == null) return false;
        }
        return state.equals(State.STATE_INTEGER)
                || state.equals(State.STATE_POINT)
                || state.equals(State.STATE_FRACTION)
                || state.equals(State.STATE_EXP_INT)
                || state.equals(State.STATE_END);
    }

    public boolean isNumber(String s) {
        // return s.matches("\\s*(([+-]?\\d+\\.?(\\d+)?([Ee][-+]?\\d+)?)|([+-]?\\.?\\d+([Ee][-+]?\\d+)?))\\s*");
        return s.matches("\\s*[+-]?((\\d+\\.?(\\d+)?)|(\\.?\\d+))([Ee][-+]?\\d+)?\\s*");
    }

    /**
     * 找规律版
     */
    public boolean isNumberWithLaw(String s) {
        boolean sign = false;
        boolean point = false;
        boolean exp = false;
        boolean number = false;
        boolean expNumber = false;
        // 去除字符串空格
        s = s.trim();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                if (!exp) number = true;
                if (exp) expNumber = true;
            } else if (c == '.') {
                if (point) return false;
                if (exp) return false;
                point = true;
            } else if (c == 'e' || c == 'E') {
                if (exp) return false;
                if (point && !number) return false;
                exp = true;
                sign = false;
            } else if (c == '-' || c == '+') {
                if (sign) return false;
                if (point && !number) return false;
                if (number && !exp) return false;
                if (expNumber && exp) return false;
                sign = true;
            } else {
                return false;
            }
        }
        return number && !exp || number && expNumber;
    }

    public boolean isNumberWithLaw1(String s) {
        // 有小数点
        boolean point = false;
        // 自然数标识
        boolean exp = false;
        // 数字标识
        boolean number = false;
        // 去除字符串空格
        s = s.trim();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                number = true;
            } else if (s.charAt(i) == '.') {
                // 指数部分,已存在小数点时排除
                if (exp || point) return false;
                point = true;
            } else if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                // 重复或者没有非指数部分
                if (exp || !number) return false;
                exp = true;
                number = false;
                point = false; // 指数部分没有小数点
            } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                // 数字后面 或 小数点后面
                if (number || point) return false;
            } else {
                return false;
            }
        }
        return number;
    }

}
