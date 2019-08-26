import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.nzgreens.common.common.enums.DeliveryModeEnum;
import com.nzgreens.common.common.result.BaseResponse;
import com.nzgreens.common.entity.ShoppingCart;
import com.nzgreens.common.entity.Users;
import com.nzgreens.common.entity.extend.UserOrderDTO;
import com.nzgreens.common.service.ShoppingCartService;
import com.nzgreens.common.service.UserOrderService;
import com.nzgreens.common.service.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by sylar on 2018/4/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/*.xml" })
public class UserOrderTest {
    @Autowired
    private UserOrderService userOrderService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UsersService usersService;

    @Test
    public void  getOrder(){
        List<ShoppingCart> shoppingCartList = shoppingCartService.selectList(
                new EntityWrapper<ShoppingCart>().in(ShoppingCart.ID,"5"));
        Users users = usersService.selectById(2);
        try {
            BaseResponse<UserOrderDTO> response = userOrderService.generatorOrderTx(shoppingCartList, users , DeliveryModeEnum._DELIVERY,1L);
            System.out.println(JSON.toJSONString(response));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
