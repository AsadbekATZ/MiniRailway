package com.example.MiniRailway.domain.dto;

import com.example.MiniRailway.domain.entity.ticket.TicketEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    String fullName;
    String username;
    String password;
}
