def solve(eq,var='X'):
    try:
        eq =eq.split('=')
        eq1 = eq[0]+'-('+eq[1]+')'
        # eq1 = eq.replace("=","-(")+")"
        c = eval(eq1,{var:1j})
        if -c.real%c.imag ==0:
            return -c.real//c.imag
        else:
            return -1
    except:
        return -1
import sys
s = sys.stdin.readline().strip()
print(int(solve(s)))
