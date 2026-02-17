"use client";

import React, { HTMLAttributes } from "react";

export interface CardProps extends HTMLAttributes<HTMLDivElement> {
  children: React.ReactNode;
  className?: string;
}

export default function Card({ children, className = "", ...props }: CardProps) {
  const classes = `bg-white rounded-xl shadow-md p-4 ${className}`.trim();
  return (
    <div className={classes} {...props}>
      {children}
    </div>
  );
}
