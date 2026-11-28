package com.spring.ai.service.impl;

import com.spring.ai.service.AIOrderSupportAssistant;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIOrderSupportAssistantImpl implements AIOrderSupportAssistant {

    @Value("classpath:prompts/order_system_template.st")
    private Resource systemPromptResource;

    @Value("classpath:prompts/order_policy_template.st")
    private Resource policyPromptResource;

    private final ChatClient geminiAIChatClient;

    public AIOrderSupportAssistantImpl(ChatClient geminiAIChatClient) {
        this.geminiAIChatClient = geminiAIChatClient;
    }

    @Override
    public String assist(String customerName, String orderId, String issueDescription) throws Exception {

        OrderEntity order = getOrderDetails(orderId, customerName);

        return geminiAIChatClient.prompt().
                system(systemPromptResource).
                system(policyPromptResource).
                user("""
                        Customer Name: %s
                        Order ID: %s
                        Issue Description: %s
                        - Delivery Date: %s
                        - Order Status: %s
                       """.formatted(order.customerName(), order.orderId(), issueDescription,order.deliveryDate(),order.orderStatus()))
                .call()
                .content();
    }



    private record OrderEntity(String orderId, String customerName, String orderStatus, String deliveryDate) {
    }

    private OrderEntity getOrderDetails(String orderId, String customerName) throws Exception {

        List<OrderEntity> orders = List.of(
                new OrderEntity("ORD123", "Alice Smith", "Shipped", "2024-06-15"),
                new OrderEntity("ORD124", "Bob Johnson", "Processing", "2024-06-20"),
                new OrderEntity("ORD125", "Charlie Brown", "Delivered", "2024-06-10"),
                new OrderEntity("ORD126", "Diana Prince", "Cancelled", "N/A"),
                new OrderEntity("ORD127", "Ethan Hunt", "Shipped", "2024-06-18")
        );

        return orders.stream()
                .filter(order -> order.orderId().equals(orderId) && order.customerName().equals(customerName))
                .findFirst()
                .orElseThrow(() -> new Exception(("Order not found")));

    }
}
